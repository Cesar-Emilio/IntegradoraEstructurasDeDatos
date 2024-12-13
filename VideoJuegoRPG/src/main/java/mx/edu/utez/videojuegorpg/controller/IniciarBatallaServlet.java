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
            ObjectMapper mapper = new ObjectMapper();
            JsonNode body = mapper.readTree(request.getReader());

            String playerId = body.get("playerId").asText();
            String enemyId = body.get("enemyId").asText();
            String abilityId = body.get("abilityId").asText();
            int nivel = body.get("nivel").asInt();

            Personaje personaje = obtenerPersonajePorId(playerId, request);
            Enemigo enemigo = obtenerEnemigoPorId(enemyId, nivel);
            Habilidad habilidadSeleccionada = personaje.getHabilidades().get(Integer.parseInt(abilityId));

            if (habilidadSeleccionada.isFueUsada()) {
                throw new IllegalArgumentException("La habilidad '" + habilidadSeleccionada.getNombre() + "' ya fue utilizada y no puede volver a usarse.");
            }

            Batalla batalla = new Batalla();
            String resultado = batalla.ejecutarBatalla(personaje, enemigo, habilidadSeleccionada);

            Map<String, Object> resultadoJson = new HashMap<>();
            resultadoJson.put("resultado", resultado);
            resultadoJson.put("habilidadId", abilityId);
            resultadoJson.put("habilidadFueUsada", habilidadSeleccionada.isFueUsada());
            resultadoJson.put("enemigoMuerto", enemigo.getSalud() <= 0);
            resultadoJson.put("personajeMuerto", personaje.getSalud() <= 0);
            resultadoJson.put("enemyId", enemyId);

            response.getWriter().write(mapper.writeValueAsString(resultadoJson));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
            e.printStackTrace();
        }
    }

    private Personaje obtenerPersonajePorId(String playerId, HttpServletRequest request) {
        ArrayList<Personaje> personajes = (ArrayList<Personaje>) request.getSession().getAttribute("selectedPlayers");
        for (int i = 0; i < personajes.size(); i++) {
            if (("playerCard" + (i + 1)).equals(playerId)) {
                return personajes.get(i);
            }
        }
        throw new IllegalArgumentException("No se encontró un personaje con el ID: " + playerId);
    }

    private Enemigo obtenerEnemigoPorId(String enemyId, int nivel) {
        ArrayList<Enemigo> enemigos = EnemigosConfig.obtenerEnemigos(nivel);
        if (enemigos == null || enemigos.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron enemigos para el nivel: " + nivel);
        }

        // Obtener el índice desde el ID
        int enemyIndex;
        try {
            enemyIndex = Integer.parseInt(enemyId.replace("enemyCard", "")) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El ID del enemigo no es válido: " + enemyId);
        }

        // Verificar si el índice está dentro del rango de la lista
        if (enemyIndex >= 0 && enemyIndex < enemigos.size()) {
            return enemigos.get(enemyIndex);
        } else {
            throw new IllegalArgumentException("El índice del enemigo está fuera de rango: " + enemyIndex);
        }
    }
}
