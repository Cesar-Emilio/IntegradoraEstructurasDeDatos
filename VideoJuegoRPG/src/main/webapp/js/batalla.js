document.addEventListener('DOMContentLoaded', function () {
    const enemyCards = document.querySelectorAll('.enemy-cards .card');
    const playerCards = document.querySelectorAll('.player-cards .card');
    const abilityContainer = document.getElementById('abilityContainer');
    let selectedPlayer = null;
    let selectedAbility = null;

    // Mostrar habilidades del jugador seleccionado
    function showPlayerAbilities(playerCard) {
        abilityContainer.innerHTML = ''; // Limpiar habilidades previas

        const abilitySpans = playerCard.querySelectorAll('.ability-data');

        abilitySpans.forEach((span, index) => {
            const abilityName = span.getAttribute('data-ability-name');
            const abilityId = span.getAttribute('data-ability-id');

            // Crear el botón para la habilidad
            const abilityButton = document.createElement('button');
            abilityButton.textContent = abilityName;
            abilityButton.classList.add('ability-button');
            abilityButton.setAttribute('data-ability-id', abilityId);

            // Verificar si es la cuarta habilidad y si ya fue usada
            if (index === 3 && span.getAttribute('data-used') === 'true') {
                abilityButton.disabled = true; // Deshabilitar solo la cuarta habilidad
                abilityButton.classList.add('used'); // Opcional: estilo para habilidades usadas
            }

            // Añadir evento para resaltar la habilidad seleccionada
            abilityButton.addEventListener('click', function () {
                if (!abilityButton.disabled) {
                    document.querySelectorAll('.ability-button').forEach(btn => btn.classList.remove('selected'));
                    this.classList.add('selected');
                    selectedAbility = this.getAttribute('data-ability-id');

                    // Permitir seleccionar un enemigo para atacar
                    selectEnemyForAttack();
                }
            });

            abilityContainer.appendChild(abilityButton);
        });
    }

    // Permitir la selección de un enemigo para el ataque
    function selectEnemyForAttack() {
        enemyCards.forEach(card => {
            if (!card.classList.contains('dead')) { // Solo permitir la selección si el enemigo no está muerto
                card.removeEventListener('click', enemySelectionHandler);
                card.addEventListener('click', enemySelectionHandler);
            }
        });
    }

    // Manejo de selección de enemigo
    function enemySelectionHandler() {
        const enemyId = this.id;
        const playerId = selectedPlayer.getAttribute('id');

        iniciarCombate(playerId, enemyId, selectedAbility);
    }

    // Iniciar el combate
    function iniciarCombate(playerId, enemyId, abilityId) {
        const playerCard = document.getElementById(playerId);
        const enemyCard = document.getElementById(enemyId);
        const nivel = new URLSearchParams(window.location.search).get('nivel');

        playerCard.classList.add('move-to-center-player');
        enemyCard.classList.add('move-to-center-enemy');

        fetch('IniciarBatallaServlet', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ playerId, enemyId, abilityId, nivel })
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                const resultadoDiv = document.getElementById('resultadoCombate');
                resultadoDiv.innerHTML = `<p>${data.resultado.replaceAll('\n', '<br>')}</p>`;

                // Deshabilitar al enemigo si murió
                if (data.enemigoMuerto) {
                    enemyCard.classList.add('dead');
                    enemyCard.style.pointerEvents = 'none';
                    enemyCard.style.opacity = '0.5';
                    enemyCard.offsetHeight;
                }

                // Deshabilitar al personaje si murió
                if (data.personajeMuerto) {
                    playerCard.classList.add('dead');
                    playerCard.style.pointerEvents = 'none';
                    playerCard.style.opacity = '0.5';
                    playerCard.offsetHeight;
                }

                // Si la habilidad fue usada, deshabilitarla
                if (data.habilidadId === '3' && data.habilidadFueUsada) {
                    const usedButton = document.querySelector(`.ability-button[data-ability-id="${abilityId}"]`);
                    if (usedButton) {
                        usedButton.disabled = true;
                        usedButton.classList.add('used'); // Opcional: estilo de habilidad usada
                    }
                }

                setTimeout(() => {
                    playerCard.classList.remove('move-to-center-player');
                    enemyCard.classList.remove('move-to-center-enemy');
                }, 2000);
            })
            .catch(error => console.error('Error al iniciar el combate:', error));
    }

    // Asignar eventos de selección a las cartas de los jugadores
    playerCards.forEach(card => {
        card.addEventListener('click', function () {
            playerCards.forEach(c => c.classList.remove('selected-player'));
            this.classList.add('selected-player');
            selectedPlayer = this;

            showPlayerAbilities(this);
        });
    });
});