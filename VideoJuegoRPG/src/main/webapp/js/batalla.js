document.addEventListener('DOMContentLoaded', function () {
    const enemyCards = document.querySelectorAll('.enemy-cards .card');
    const playerCards = document.querySelectorAll('.player-cards .card');
    const abilityContainer = document.getElementById('abilityContainer');
    let selectedPlayer = null;
    let selectedEnemy = null;
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

                    // Ejecutar combate
                    if (selectedPlayer && selectedEnemy && selectedAbility) {
                        iniciarCombate(selectedPlayer.getAttribute('id'), selectedEnemy.getAttribute('id'), selectedAbility);
                    }
                }
            });

            abilityContainer.appendChild(abilityButton);
        });
    }

    //Funcion para mostrar el versus en el centro
    function showVS() {
        const vsContainer = document.getElementById('vs-container');
        vsContainer.textContent = 'VS'; // Añadir el texto VS
        vsContainer.style.opacity = '1';
        vsContainer.style.transform = 'translate(-50%, -50%) scale(1.2)';

        setTimeout(() => {
            vsContainer.style.opacity = '0';
            vsContainer.style.transform = 'translate(-50%, -50%) scale(1)';
        }, 1500); // Ocultar después de 1.5 segundos
    }

    // Función para marcar la cuarta habilidad como usada
    function marcarHabilidadComoUsada(playerCard, abilityId) {
        const abilitySpan = playerCard.querySelector(`.ability-data[data-ability-id="${abilityId}"]`);
        if (abilitySpan) {
            abilitySpan.setAttribute('data-used', 'true'); // Marcar como usada
        }
    }



    // Manejo de selección de jugador aliado
    playerCards.forEach(card => {
        card.addEventListener('click', function () {
            playerCards.forEach(c => c.classList.remove('selected-player'));
            this.classList.add('selected-player');
            selectedPlayer = this;

            // Limpiar selección previa de enemigo
            enemyCards.forEach(c => c.classList.remove('selected-enemy'));
            selectedEnemy = null;

            abilityContainer.innerHTML = ''; // Ocultar habilidades hasta seleccionar enemigo
        });
    });

    // Manejo de selección de enemigo
    enemyCards.forEach(card => {
        card.addEventListener('click', function () {
            if (selectedPlayer) { // Solo permitir selección de enemigo si hay un aliado seleccionado
                enemyCards.forEach(c => c.classList.remove('selected-enemy'));
                this.classList.add('selected-enemy');
                selectedEnemy = this;

                // Mostrar habilidades del jugador seleccionado
                showPlayerAbilities(selectedPlayer);
            }
        });
    });

    // Iniciar el combate
    function iniciarCombate(playerId, enemyId, abilityId) {
        showVS(); // Mostrar "VS" en la pantalla
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
                if (response.redirected) {
                    window.location.href = response.url; // Redirige si es el fin del juego
                    return;
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
                }

                // Deshabilitar al personaje si murió
                if (data.personajeMuerto) {
                    playerCard.classList.add('dead');
                    playerCard.style.pointerEvents = 'none';
                    playerCard.style.opacity = '0.5';
                }

                // Si la habilidad fue usada, deshabilitarla
                if (data.habilidadId === '3' && data.habilidadFueUsada) {
                    const usedButton = document.querySelector(`.ability-button[data-ability-id="${abilityId}"]`);
                    if (usedButton) {
                        usedButton.disabled = true;
                        usedButton.classList.add('used'); // Opcional: estilo de habilidad usada
                    }

                    // Marcar la habilidad como usada en el jugador
                    marcarHabilidadComoUsada(playerCard, abilityId);
                }

                setTimeout(() => {
                    playerCard.classList.remove('move-to-center-player');
                    enemyCard.classList.remove('move-to-center-enemy');

                    // Deseleccionar personajes y habilidad
                    playerCards.forEach(c => c.classList.remove('selected-player'));
                    enemyCards.forEach(c => c.classList.remove('selected-enemy'));
                    document.querySelectorAll('.ability-button').forEach(btn => btn.classList.remove('selected'));
                    selectedPlayer = null;
                    selectedEnemy = null;
                    selectedAbility = null;

                    abilityContainer.innerHTML = ''; // Limpiar habilidades
                }, 2000);
            })
            .catch(error => console.error('Error al iniciar el combate:', error));
    }
});
