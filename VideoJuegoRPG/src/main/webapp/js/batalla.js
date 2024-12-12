document.addEventListener('DOMContentLoaded', function() {
    const playerCards = document.querySelectorAll('.player-cards .card');
    const enemyCards = document.querySelectorAll('.enemy-cards .card');
    const versusText = document.getElementById('versusText');

    function initializeCards(data) {
        data.playerCards.forEach((card, index) => {
            playerCards[index].style.backgroundImage = `url(${card.imageSrc})`;
            playerCards[index].dataset.hp = card.hp;
            playerCards[index].dataset.attack = card.attack;
        });

        data.enemyCards.forEach((card, index) => {
            enemyCards[index].style.backgroundImage = `url(${card.imageSrc})`;
            enemyCards[index].dataset.hp = card.hp;
            enemyCards[index].dataset.attack = card.attack;
        });
    }

    function handleCardAttack(playerCard, enemyCard) {
        playerCard.classList.add('move-to-center');
        enemyCard.classList.add('move-to-center');
        versusText.classList.add('show');

        setTimeout(() => {
            playerCard.classList.remove('move-to-center');
            enemyCard.classList.remove('move-to-center');
            versusText.classList.remove('show');
        }, 2000);
    }

    playerCards.forEach(card => {
        card.addEventListener('click', function() {
            const enemyCard = enemyCards[Math.floor(Math.random() * enemyCards.length)];
            handleCardAttack(card, enemyCard);
        });
    });
    fetch('/IniciarBatallaServlet')
        .then(response => response.json())
        .then(data => {
            console.log('Datos recibidos:', data);
            initializeCards(data);
        })
        .catch(error => console.error('Error:', error));
});
