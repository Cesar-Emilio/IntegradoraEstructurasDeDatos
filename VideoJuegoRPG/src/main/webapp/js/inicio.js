document.addEventListener('DOMContentLoaded', () => {
    const characterSlots = document.querySelectorAll('.character-slot');
    const startGameBtn = document.getElementById('start-game-btn');
    const characterModal = document.getElementById('character-modal');
    const modalContent = document.querySelector('.modal-content');
    const characterCarousel = document.querySelector('.character-carousel');
    const selectCharacterBtn = document.getElementById('select-character-btn');

    const roleSelectWrapper = document.createElement('div');
    roleSelectWrapper.className = 'role-select-container styled-select-wrapper';

    const roleSelect = document.createElement('select');
    roleSelect.id = 'role-select';
    roleSelect.className = 'styled-select';

    const roles = [
        { id: 1, name: 'Guerrero' },
        { id: 2, name: 'Mago' },
        { id: 3, name: 'Arquero' },
    ];

    roles.forEach(role => {
        const option = document.createElement('option');
        option.value = role.id;
        option.textContent = role.name;
        roleSelect.appendChild(option);
    });

    roleSelectWrapper.appendChild(roleSelect);
    modalContent.insertBefore(roleSelectWrapper, characterCarousel);

    let characters = [
        { id: 1, name: 'Erick', image: 'images/characters/Erick.png', attack: 80, speed: 60, defense: 70 },
        { id: 2, name: 'Big', image: 'images/characters/Big.png', attack: 50, speed: 80, defense: 90 },
        { id: 3, name: 'Cesar', image: 'images/characters/Cesar.png', attack: 70, speed: 70, defense: 80 },
        { id: 4, name: 'Andres', image: 'images/characters/Andres.png', attack: 100, speed: 50, defense: 60 },
        { id: 5, name: 'Sebas', image: 'images/characters/Sebas.png', attack: 60, speed: 90, defense: 50 },
        { id: 6, name: 'Choforo', image: 'images/characters/Chris.png', attack: 65, speed: 75, defense: 85 },
    ];

    let currentCharacterIndex = 0;
    let selectedSlot = null;
    let selectedCharacters = {};

    characterSlots.forEach((slot, index) => {
        slot.dataset.slot = index;
        slot.innerHTML = `<div class="add-character-icon">+</div>`;
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
        const character = characters[currentCharacterIndex];
        characterCarousel.innerHTML = `
            <div class="character-card">
                <img src="${character.image}" alt="${character.name}">
                <h3>${character.name}</h3>
                <div class="stats">
                    <div class="stat-group">
                        <div class="stat-label">Ataque</div>
                        <div class="stat-bar-container">
                            <span style="width: ${character.attack}%" class="bar attack"></span>
                        </div>
                        <div class="stat-value">${character.attack}</div>
                    </div>
                    <div class="stat-group">
                        <div class="stat-label">Velocidad</div>
                        <div class="stat-bar-container">
                            <span style="width: ${character.speed}%" class="bar speed"></span>
                        </div>
                        <div class="stat-value">${character.speed}</div>
                    </div>
                    <div class="stat-group">
                        <div class="stat-label">Defensa</div>
                        <div class="stat-bar-container">
                            <span style="width: ${character.defense}%" class="bar defense"></span>
                        </div>
                        <div class="stat-value">${character.defense}</div>
                    </div>
                </div>
            </div>
        `;
    }

    selectCharacterBtn.addEventListener('click', () => {
        const selectedRole = roles.find(role => role.id == roleSelect.value).name;
        const selectedCharacter = characters[currentCharacterIndex];

        if (selectedSlot) {
            for (const [slotId, charId] of Object.entries(selectedCharacters)) {
                if (charId === selectedCharacter.id) {
                    const previousSlot = document.querySelector(`.character-slot[data-slot="${slotId}"]`);
                    previousSlot.classList.remove('selected');
                    previousSlot.innerHTML = '<div class="add-character-icon">+</div>';
                    delete selectedCharacters[slotId];
                    break;
                }
            }

            selectedSlot.innerHTML = `
                <img src="${selectedCharacter.image}" alt="${selectedCharacter.name}">
                <p>${selectedCharacter.name}</p>
                <p class="role">${selectedRole}</p>
            `;
            selectedSlot.classList.add('selected');
            selectedCharacters[selectedSlot.dataset.slot] = selectedCharacter.id;

            characterModal.style.display = 'none';
            checkAllSlotsSelected();
        }
    });

    function checkAllSlotsSelected() {
        const allSelected = Object.keys(selectedCharacters).length === characterSlots.length;
        startGameBtn.disabled = !allSelected;
    }

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

    const carouselControls = document.createElement('div');
    carouselControls.className = 'carousel-controls';
    carouselControls.appendChild(prevBtn);
    carouselControls.appendChild(nextBtn);
    modalContent.insertBefore(carouselControls, selectCharacterBtn);

    characterModal.addEventListener('click', (event) => {
        if (!modalContent.contains(event.target)) {
            closeModal();
        }
    });

    document.addEventListener('keydown', (event) => {
        if (event.key === 'Escape' && characterModal.style.display === 'block') {
            closeModal();
        }
    });

    function closeModal() {
        characterModal.style.display = 'none';
    }

    startGameBtn.addEventListener('click', () => {
        if (!startGameBtn.disabled) {
            const selectedCharactersArray = Array.from(document.querySelectorAll('.character-slot.selected'))
                .map(slot => ({
                    name: slot.querySelector('p')?.textContent || '',
                    role: slot.querySelector('.role')?.textContent || ''
                }));

            fetch('SeleccionPersonajesServlet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ personajes: selectedCharactersArray })
            })
                .then(response => response.json())
                .then(data => {
                    console.log('Respuesta del servidor:', data);
                    window.location.href = 'batalla.jsp';
                })
                .catch(error => console.error('Error:', error));
        }
    });
});