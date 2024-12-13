<%@ page import="mx.edu.utez.videojuegorpg.model.Personaje" %>
<%@ page import="mx.edu.utez.videojuegorpg.dataStructures.ArrayList" %>
<%@ page import="java.io.File" %>
<%@ page import="mx.edu.utez.videojuegorpg.model.Enemigo" %>
<%@ page import="mx.edu.utez.videojuegorpg.model.EnemigosConfig" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Obtener el nivel desde el parámetro
    String nivelParam = request.getParameter("nivel");
    int nivel = (nivelParam != null) ? Integer.parseInt(nivelParam) : 1;

    // Obtener los enemigos del nivel actual
    ArrayList<Enemigo> enemigos = EnemigosConfig.obtenerEnemigos(nivel);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Battle Arena</title>
    <link rel="stylesheet" href="css/batalla.css">
    <style>
        .character-slot.selected {
            border-color: #00ff00;
            box-shadow: -2px -2px 0 #008f00, 2px 2px 0 #008f00, -2px 2px 0 #008f00, 2px -2px 0 #008f00, inset 0 0 15px rgba(0,255,0,0.3);
        }
        .character-slot.selected_enemies {
            border-color: #ff0404;
            box-shadow: -2px -2px 0 #f00000, 2px 2px 0 #f00000, -2px 2px 0 #f00000, 2px -2px 0 #f00000, inset 0 0 15px rgba(0,255,0,0.3);
        }
    </style>
</head>
<body>
<div class="battle-container">
    <!-- Mostrar el nivel en la parte superior de la batalla -->
    <h2 class="battle-level">Nivel: <%= nivel %></h2>

    <div class="battle-arena">
        <div class="player-cards">
            <%
                ArrayList<Personaje> personajes = (ArrayList<Personaje>) session.getAttribute("selectedPlayers");
                if (personajes != null && personajes.size() > 0) {
                    for (int i = 0; i < personajes.size(); i++) {
                        Personaje p = personajes.get(i);
            %>
            <div class="card character-slot <%= (i == 0) ? "selected" : "" %>"
                 id="playerCard<%= i + 1 %>"
                 style="background-image: url('<%=request.getContextPath()%>/<%=p.getImagen()%>');">
                <%-- Store abilities as data attributes --%>
                <% for (int j = 0; j < p.getHabilidades().size(); j++) { %>
                <span class="ability-data"
                      data-ability-name="<%= p.getHabilidades().get(j).getNombre() %>"
                      data-ability-id="<%= j %>"></span>
                <% } %>
            </div>
            <%
                    }
                }
            %>
        </div>

        <div id="resultadoCombate" class="resultado"></div>

        <div class="enemy-cards">
            <%
                if (enemigos != null && enemigos.size() > 0) {
                    for (int i = 0; i < enemigos.size(); i++) {
                        Enemigo enemigo = enemigos.get(i);
            %>
            <div class="card character-slot <%= (i == 0) ? "selected_enemies" : "" %>"
                 id="enemyCard<%= i + 1 %>"
                 style="background-image: url('<%= request.getContextPath() %>/<%= enemigo.getImagen() %>');">
            </div>
            <%
                }
            } else {
            %>
            <p>No se encontraron enemigos para este nivel.</p>
            <%
                }
            %>
        </div>
    </div>

    <!-- New ability selection container -->
    <div id="abilityContainer" class="ability-container">
        <!-- Abilities will be dynamically populated here -->
    </div>
</div>

<script>
    // Script para manejar la selección dinámica de personajes o enemigos
    document.querySelectorAll('.card.character-slot').forEach(slot => {
        slot.addEventListener('click', () => {
            // Remover la clase `selected` de todos los slots
            document.querySelectorAll('.card.character-slot').forEach(s => s.classList.remove('selected', 'selected_enemies'));

            // Agregar la clase correspondiente al slot seleccionado
            if (slot.parentElement.classList.contains('enemy-cards')) {
                slot.classList.add('selected_enemies');
            } else {
                slot.classList.add('selected');
            }
        });
    });
</script>
<script src="js/batalla.js"></script>
</body>
</html>
