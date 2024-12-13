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

        abilitySpans.forEach(span => {
            const abilityName = span.getAttribute('data-ability-name');
            const abilityId = span.getAttribute('data-ability-id');

            const abilityButton = document.createElement('button');
            abilityButton.textContent = abilityName;
            abilityButton.classList.add('ability-button');
            abilityButton.setAttribute('data-ability-id', abilityId);

            abilityButton.addEventListener('click', function () {
                selectedAbility = this.getAttribute('data-ability-id');
                selectEnemyForAttack();
            });

            abilityContainer.appendChild(abilityButton);
        });
    }

    // Permitir la selección de un enemigo para el ataque
    function selectEnemyForAttack() {
        enemyCards.forEach(card => {
            card.removeEventListener('click', enemySelectionHandler); // Quitar listeners previos
            card.addEventListener('click', enemySelectionHandler); // Añadir nuevo listener
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

        playerCard.classList.add('move-to-center-player');
        enemyCard.classList.add('move-to-center-enemy');

        abilityContainer.innerHTML = ''; // Limpiar habilidades

        fetch('IniciarBatallaServlet', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ playerId, enemyId, abilityId })
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                const resultadoDiv = document.getElementById('resultadoCombate');
                resultadoDiv.innerHTML = `<p>${data.resultado}</p>`;

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

            showPlayerAbilities(this); // Mostrar habilidades del jugador
        });
    });
});
