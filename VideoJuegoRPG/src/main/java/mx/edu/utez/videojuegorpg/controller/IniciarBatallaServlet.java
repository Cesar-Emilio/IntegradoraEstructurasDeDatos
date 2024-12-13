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
import mx.edu.utez.videojuegorpg.model.Personaje;
import mx.edu.utez.videojuegorpg.util.Batalla;

import java.io.IOException;
import java.util.*;

@WebServlet("/IniciarBatallaServlet")
public class IniciarBatallaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Parámetros recibidos:");
        request.getParameterMap().forEach((key, value) -> {
            System.out.println("Clave: " + key + ", Valor: " + Arrays.toString(value));
        });

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode body = mapper.readTree(request.getReader());

            String playerId = body.get("playerId").asText();
            String enemyId = body.get("enemyId").asText();

            // Lógica para obtener personaje y enemigo
            Personaje personaje = obtenerPersonajePorId(playerId, request);
            String nivelParam = request.getParameter("nivel");
            int nivel = (nivelParam != null && !nivelParam.isEmpty()) ? Integer.parseInt(nivelParam) : 1;

            Enemigo enemigo = obtenerEnemigoPorId(enemyId, nivel);

            // Devuelve un mensaje de éxito como ejemplo
            Map<String, String> resultado = new HashMap<>();
            resultado.put("resultado", "Combate iniciado entre " + personaje.getNombre() + " y " + enemigo.getNombre());
            response.getWriter().write(mapper.writeValueAsString(resultado));

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
            e.printStackTrace();
        }
    }


    private Personaje obtenerPersonajePorId(String playerId, HttpServletRequest request) {
        // Obtener la lista de personajes desde la sesión
        ArrayList<Personaje> personajes = (ArrayList<Personaje>) request.getSession().getAttribute("selectedPlayers");
        if (personajes == null) {
            throw new IllegalArgumentException("No se encontraron personajes en la sesión.");
        }

        // Buscar el personaje por ID
        for (int i = 0; i < personajes.size(); i++) {
            if (("playerCard" + (i + 1)).equals(playerId)) {
                return personajes.get(i);
            }
        }

        throw new IllegalArgumentException("No se encontró un personaje con el ID: " + playerId);
    }

    private Enemigo obtenerEnemigoPorId(String enemyId, int nivel) {
        // Obtener la lista de enemigos del nivel desde EnemigosConfig
        ArrayList<Enemigo> enemigos = EnemigosConfig.obtenerEnemigos(nivel);
        if (enemigos == null || enemigos.size() == 0) {
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
