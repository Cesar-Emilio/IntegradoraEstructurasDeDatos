<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Selección de Personajes</title>
    <link rel="stylesheet" href="css/inicio.css">
</head>
<body>
<div class="container">
    <h1>PERSONAJES</h1>

    <div class="character-selection-grid">
        <% for(int i = 0; i < 5; i++) { %>
        <div class="character-slot" data-slot="<%= i %>">
            <div class="add-character-icon">+</div>
        </div>
        <% } %>
    </div>

    <button id="start-game-btn" class="start-game-btn" disabled>Iniciar Juego</button>
</div>

<div id="character-modal" class="modal">
    <div class="modal-content">
        <div class="character-carousel">
            <!-- Aquí van los personajes :b usando esa madre de java -->
        </div>
        <button id="select-character-btn">Agregar Personaje</button>
    </div>
    <div>
        <select>
            <option value="warrior">Warrior</option>
            <option value="mage">Mage</option>
            <option value="archer">Archer</option>
        </select>
        <button>Select Role</button>
    </div>
</div>

<script src="js/inicio.js"></script>
</body>
</html>