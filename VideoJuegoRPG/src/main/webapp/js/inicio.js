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
        { id: 1, name: 'Erick', image: 'images/characters/Erick.png', salud: 80, velocidad: 60, defensa: 70 },
        { id: 2, name: 'Big', image: 'images/characters/Big.png', salud: 50, velocidad: 80, defensa: 90 },
        { id: 3, name: 'Cesar', image: 'images/characters/Cesar.png', salud: 70, velocidad: 70, defensa: 80 },
        { id: 4, name: 'Andres', image: 'images/characters/Andres.png', salud: 100, velocidad: 50, defensa: 60 },
        { id: 5, name: 'Sebas', image: 'images/characters/Sebas.png', salud: 60, velocidad: 90, defensa: 50 },
        { id: 6, name: 'Choforo', image: 'images/characters/Chris.png', salud: 65, velocidad: 75, defensa: 85 },
    ];

    let currentCharacterIndex = 0;
    let selectedSlot = null;
    let selectedCharacters = {};

    characterSlots.forEach((slot, index) => {
        slot.dataset.slot = index;
        slot.innerHTML = '<div class="add-character-icon">+</div>';
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
        characterCarousel.innerHTML =
            `<div class="character-card">
                <img src="${character.image}" alt="${character.name}">
                <h3>${character.name}</h3>
                <div class="stats">
                    <div class="stat-group">
                        <div class="stat-label">Salud</div>
                        <div class="stat-bar-container">
                            <span style="width: ${character.salud}%" class="bar health"></span>
                        </div>
                        <div class="stat-value">${character.salud}</div>
                    </div>
                    <div class="stat-group">
                        <div class="stat-label">Velocidad</div>
                        <div class="stat-bar-container">
                            <span style="width: ${character.velocidad}%" class="bar speed"></span>
                        </div>
                        <div class="stat-value">${character.velocidad}</div>
                    </div>
                    <div class="stat-group">
                        <div class="stat-label">Defensa</div>
                        <div class="stat-bar-container">
                            <span style="width: ${character.defensa}%" class="bar defense  "></span>
                        </div>
                        <div class="stat-value">${character.defensa}</div>
                    </div>
                </div>
            </div>`;
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
                .map(slot => {
                    const characterId = parseInt(selectedCharacters[slot.dataset.slot]); // Obtener el ID correcto del personaje seleccionado
                    const character = characters.find(c => c.id === characterId); // Encontrar el personaje por su ID

                    // Validar que el personaje exista
                    if (character) {
                        return {
                            name: character.name,
                            role: slot.querySelector('.role')?.textContent || '', // Obtener el rol desde el slot
                            salud: character.salud,
                            velocidad: character.velocidad,
                            defensa: character.defensa,
                            image: character.image
                        };
                    }

                    //Si el personaje no existe retornamos uno vacio (Esto no ejecutara debido a la validacion del boton deshabilitado)
                    return {
                        name: '',
                        role: '',
                        salud: 0,
                        velocidad: 0,
                        defensa: 0,
                        image: ''
                    };
                });

            fetch('InicioServlet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ personajes: selectedCharactersArray })
            })
                .then(response => response.json())
                .then(data => {
                    console.log('Respuesta del servidor:', data);
                    window.location.href = 'niveles.jsp';
                })
                .catch(error => console.error('Error:', error));
        }
    });
});