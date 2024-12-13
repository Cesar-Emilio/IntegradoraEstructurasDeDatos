document.addEventListener('DOMContentLoaded', function () {
    const enemyCards = document.querySelectorAll('.enemy-cards .card');
    const playerCards = document.querySelectorAll('.player-cards .card');
    const abilityContainer = document.getElementById('abilityContainer');
    let selectedPlayer = null;
    let selectedAbility = null;

    // Function to show abilities for a selected player
    function showPlayerAbilities(playerCard) {
        // Clear previous abilities
        abilityContainer.innerHTML = '';

        // Get the ability data from the player card's child spans
        const abilitySpans = playerCard.querySelectorAll('.ability-data');

        abilitySpans.forEach(span => {
            const abilityName = span.getAttribute('data-ability-name');
            const abilityId = span.getAttribute('data-ability-id');

            // Create ability button
            const abilityButton = document.createElement('button');
            abilityButton.textContent = abilityName;
            abilityButton.classList.add('ability-button');
            abilityButton.setAttribute('data-ability-id', abilityId);

            // Add click event to ability button
            abilityButton.addEventListener('click', function() {
                selectedAbility = this.getAttribute('data-ability-id');
                // Proceed to select enemy
                selectEnemyForAttack(playerCard);
            });

            abilityContainer.appendChild(abilityButton);
        });
    }

    // Function to select enemy for attack
    function selectEnemyForAttack(playerCard) {
        // Remove any previous enemy selection listeners
        enemyCards.forEach(card => {
            card.removeEventListener('click', enemySelectionHandler);
        });

        // Add click event to enemy cards for target selection
        enemyCards.forEach(card => {
            card.addEventListener('click', enemySelectionHandler);
        });
    }

    // Enemy selection handler
    function enemySelectionHandler() {
        const playerId = selectedPlayer.getAttribute('data-player-id');
        const enemyId = this.id;

        // Initiate combat with selected player, enemy, and ability
        iniciarCombate(playerId, enemyId, selectedAbility);
    }

    // Function to start combat
    function iniciarCombate(playerId, enemyId, abilityId) {
        // Move the selected cards to center
        const playerCard = document.getElementById(playerId);
        const enemyCard = document.getElementById(enemyId);

        playerCard.classList.add('move-to-center-player');
        enemyCard.classList.add('move-to-center-enemy');

        // Clear ability container
        abilityContainer.innerHTML = '';

        // Send combat request to server
        fetch(`IniciarBatallaServlet`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                playerId: playerId,
                enemyId: enemyId,
                abilityId: abilityId
            })
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                console.log('Resultado del combate:', data);
                const resultadoDiv = document.getElementById('resultadoCombate');
                resultadoDiv.innerHTML = `<p>${data.resultado}</p>`;

                // Reset cards after animation
                setTimeout(() => {
                    playerCard.classList.remove('move-to-center-player');
                    enemyCard.classList.remove('move-to-center-enemy');
                }, 2000);
            })
            .catch(error => console.error('Error al iniciar el combate:', error));
    }

    // Add click events to player cards
    playerCards.forEach(card => {
        card.addEventListener('click', function() {
            // Deselect all other cards
            playerCards.forEach(c => c.classList.remove('selected-player'));

            // Select current card
            this.classList.add('selected-player');
            selectedPlayer = this;

            // Show abilities for this player
            showPlayerAbilities(this);
        });
    });
});