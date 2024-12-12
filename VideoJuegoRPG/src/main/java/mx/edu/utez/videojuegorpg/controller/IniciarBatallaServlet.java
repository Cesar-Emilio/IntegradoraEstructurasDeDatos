package mx.edu.utez.videojuegorpg.controller;

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

import java.io.IOException;
import java.util.*;

@WebServlet("/IniciarBatallaServlet")
public class IniciarBatallaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Obtener los aliados guardados en sesión
        ArrayList<Personaje> selectedPlayers = (ArrayList<Personaje>) request.getSession().getAttribute("selectedPlayers");

        // Obtener nivel aleatorio (1 a 10)
        int nivelAleatorio = new Random().nextInt(10) + 1;

        // Obtener enemigos para ese nivel
        ArrayList<Enemigo> enemigos = EnemigosConfig.obtenerEnemigos(nivelAleatorio);

        // Preparar JSON de respuesta
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> responseData = new HashMap<>();

        // Construir playerCards
        ArrayList<Map<String, Object>> playerCards = new ArrayList<>();
        if (selectedPlayers != null) {
            for (int i = 0; i < selectedPlayers.size(); i++) {
                Map<String, Object> cardData = new HashMap<>();
                cardData.put("imageSrc", selectedPlayers.get(i).getImagen());
                cardData.put("hp", selectedPlayers.get(i).getSalud());
                cardData.put("attack", selectedPlayers.get(i).getDefensa());
                playerCards.add(cardData);
            }
        }

        // Construir enemyCards
        ArrayList<Map<String, Object>> enemyCards = new ArrayList<>();
        for (int i = 0; i < enemigos.size(); i++) {
            Map<String, Object> cardData = new HashMap<>();
            cardData.put("imageSrc", enemigos.get(i).getImagen());
            cardData.put("hp", enemigos.get(i).getSalud());
            cardData.put("attack", enemigos.get(i).getDefensa());
            enemyCards.add(cardData);
        }
        
        responseData.put("playerCards", playerCards);
        responseData.put("enemyCards", enemyCards);

        // Devolver JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(mapper.writeValueAsString(responseData));
    }
}
