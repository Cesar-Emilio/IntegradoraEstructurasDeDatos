package mx.edu.utez.videojuegorpg.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.videojuegorpg.dataStructures.ArrayList;
import mx.edu.utez.videojuegorpg.model.Enemigo;
import mx.edu.utez.videojuegorpg.model.EnemigosConfig;
import mx.edu.utez.videojuegorpg.model.Habilidad;
import mx.edu.utez.videojuegorpg.model.Personaje;
import mx.edu.utez.videojuegorpg.util.Batalla;

import java.io.IOException;
import java.util.*;

@WebServlet("/IniciarBatallaServlet")
public class IniciarBatallaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            // Leer y procesar los datos enviados desde el cliente
            ObjectMapper mapper = new ObjectMapper();
            JsonNode body = mapper.readTree(request.getReader());

            // Obtener IDs y la habilidad seleccionada
            String playerId = body.get("playerId").asText();
            String enemyId = body.get("enemyId").asText();
            String abilityId = body.get("abilityId").asText();

            // Obtener el personaje, enemigo y habilidad seleccionada
            Personaje personaje = obtenerPersonajePorId(playerId, request);
            Enemigo enemigo = obtenerEnemigoPorId(enemyId, request);
            Habilidad habilidadSeleccionada = personaje.getHabilidades().get(Integer.parseInt(abilityId));

            // Crear una instancia de Batalla y procesar el ataque
            Batalla batalla = new Batalla();
            String resultado = batalla.ejecutarBatalla(personaje, enemigo, habilidadSeleccionada);

            // Preparar la respuesta
            Map<String, String> resultadoJson = new HashMap<>();
            resultadoJson.put("resultado", resultado);

            response.getWriter().write(mapper.writeValueAsString(resultadoJson));

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
            e.printStackTrace();
        }
    }

    private Personaje obtenerPersonajePorId(String playerId, HttpServletRequest request) {
        ArrayList<Personaje> personajes = (ArrayList<Personaje>) request.getSession().getAttribute("selectedPlayers");
        if (personajes == null) {
            throw new IllegalArgumentException("No se encontraron personajes en la sesión.");
        }

        for (int i = 0; i < personajes.size(); i++) {
            if (("playerCard" + (i + 1)).equals(playerId)) {
                return personajes.get(i);
            }
        }

        throw new IllegalArgumentException("No se encontró un personaje con el ID: " + playerId);
    }

    private Enemigo obtenerEnemigoPorId(String enemyId, HttpServletRequest request) {
        String nivelParam = request.getParameter("nivel");
        int nivel;

        // Validar y manejar el nivel por defecto
        try {
            nivel = (nivelParam != null && !nivelParam.isEmpty()) ? Integer.parseInt(nivelParam) : 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El parámetro 'nivel' no es válido: " + nivelParam);
        }

        // Obtener los enemigos para el nivel especificado
        ArrayList<Enemigo> enemigos = EnemigosConfig.obtenerEnemigos(nivel);
        if (enemigos == null || enemigos.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron enemigos para el nivel: " + nivel);
        }

        // Buscar el enemigo por ID
        for (int i = 0; i < enemigos.size(); i++) {
            if (("enemyCard" + (i + 1)).equals(enemyId)) {
                return enemigos.get(i);
            }
        }

        throw new IllegalArgumentException("No se encontró un enemigo con el ID: " + enemyId);
    }

}