document.addEventListener('DOMContentLoaded', function () {
    const enemyCards = document.querySelectorAll('.enemy-cards .card');
    const playerCards = document.querySelectorAll('.player-cards .card');

    // Inicializar las tarjetas de los personajes con solo las imágenes
    function initializePlayerCards() {
        playerCards.forEach(card => {
            const image = card.dataset.image; // Leer el atributo data-image
            if (image) {
                card.style.backgroundImage = `url(${image})`;
                card.style.backgroundSize = 'cover'; // Asegura que la imagen cubra toda la tarjeta
                card.style.backgroundPosition = 'center'; // Centra la imagen en la tarjeta
            } else {
                console.warn(`La tarjeta con id ${card.id} no tiene una imagen definida.`);
            }
        });
    }

    // Función para iniciar el combate
    function iniciarCombate(playerId) {
        const enemyIndex = Math.floor(Math.random() * enemyCards.length);
        const enemyId = enemyCards[enemyIndex].id;

        // Mover las tarjetas hacia arriba del centro
        const playerCard = document.getElementById(playerId);
        const enemyCard = document.getElementById(enemyId);

        playerCard.classList.add('move-to-center-player');
        enemyCard.classList.add('move-to-center-enemy');

        fetch(`IniciarBatallaServlet`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ playerId, enemyId })
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

                // Remover clases después de un tiempo para resetear las tarjetas
                setTimeout(() => {
                    playerCard.classList.remove('move-to-center-player');
                    enemyCard.classList.remove('move-to-center-enemy');
                }, 2000);
            })
            .catch(error => console.error('Error al iniciar el combate:', error));
    }

    // Añadir eventos de clic a las tarjetas de los jugadores
    playerCards.forEach(card => {
        card.addEventListener('click', function () {
            iniciarCombate(this.id);
        });
    });

    // Inicializar las tarjetas al cargar la página
    initializePlayerCards();
});
