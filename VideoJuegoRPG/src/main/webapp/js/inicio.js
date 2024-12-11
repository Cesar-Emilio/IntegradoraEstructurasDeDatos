document.addEventListener('DOMContentLoaded', () => {
    const characterSlots = document.querySelectorAll('.character-slot');
    const startGameBtn = document.getElementById('start-game-btn');
    const characterModal = document.getElementById('character-modal');
    const characterCarousel = document.querySelector('.character-carousel');
    const selectCharacterBtn = document.getElementById('select-character-btn');

    let selectedSlot = null;
    let characters = [
        { id: 1, name: 'Erick', image: '../images/characters/Erick.png' },
        { id: 2, name: 'Big', image: '../images/characters/Big.png' },
        { id: 3, name: 'Cesar', image: '../images/characters/Cesar.png' },
        { id: 4, name: 'Andres', image: '../images/characters/Andres.png' },
        { id: 5, name: 'Sebas', image: '../images/characters/sebas.png' },
        { id: 6, name: 'Choforo', image: '../images/characters/Chris.png' }
    ];

    let currentCharacterIndex = 0;

    characterSlots.forEach(slot => {
        if (slot.innerHTML.trim() === '') {
            slot.innerHTML = `<div class="add-character-icon">+</div>`;
        }

        slot.addEventListener('click', () => {
            if (!slot.classList.contains('selected')) {
                selectedSlot = slot;
                showCharacterModal();
            }
        });
    });

    function showCharacterModal() {
        characterModal.style.display = 'block';
        renderCharacterCarousel();
    }

    function renderCharacterCarousel() {
        characterCarousel.innerHTML = `
            <div class="character-card">
                <img src="images/${characters[currentCharacterIndex].image}" alt="${characters[currentCharacterIndex].name}">
                <h3>${characters[currentCharacterIndex].name}</h3>
            </div>
        `;
    }

    selectCharacterBtn.addEventListener('click', () => {
        if (selectedSlot) {
            const selectedCharacter = characters[currentCharacterIndex];
            selectedSlot.innerHTML = `
                <img src="images/${selectedCharacter.image}" alt="${selectedCharacter.name}">
                <p>${selectedCharacter.name}</p>
            `;
            selectedSlot.classList.add('selected');
            characterModal.style.display = 'none';
            checkAllSlotsSelected();
        }
    });

    function checkAllSlotsSelected() {
        const allSelected = Array.from(characterSlots).every(slot =>
            slot.classList.contains('selected')
        );
        startGameBtn.disabled = !allSelected;
    }

    // Navegación en el carrusel
    const carouselControls = document.createElement('div');
    carouselControls.className = 'carousel-controls';

    const prevBtn = document.createElement('button');
    prevBtn.textContent = '←';
    prevBtn.addEventListener('click', () => {
        currentCharacterIndex = (currentCharacterIndex - 1 + characters.length) % characters.length;
        renderCharacterCarousel();
    });

    const nextBtn = document.createElement('button');
    nextBtn.textContent = '→';
    nextBtn.addEventListener('click', () => {
        currentCharacterIndex = (currentCharacterIndex + 1) % characters.length;
        renderCharacterCarousel();
    });

    carouselControls.appendChild(prevBtn);
    carouselControls.appendChild(nextBtn);
    document.querySelector('.modal-content').insertBefore(carouselControls, selectCharacterBtn);

    startGameBtn.addEventListener('click', () => {
        if (!startGameBtn.disabled) {
            // JUEGOTE
            alert('¡Juego iniciado!');
        }
    });
});