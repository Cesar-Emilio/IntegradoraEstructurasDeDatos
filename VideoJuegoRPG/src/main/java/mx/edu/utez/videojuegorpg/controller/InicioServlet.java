package mx.edu.utez.videojuegorpg.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.videojuegorpg.dataStructures.ArrayList;
import mx.edu.utez.videojuegorpg.enums.Rol;
import mx.edu.utez.videojuegorpg.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/InicioServlet")
public class InicioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode;
        try {
            rootNode = mapper.readTree(request.getReader());
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"El formato del JSON es inválido\"}");
            return;
        }

        // Validar si el nodo "personajes" existe y es un arreglo
        JsonNode personajesNode = rootNode.get("personajes");
        if (personajesNode == null || !personajesNode.isArray()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Faltan los personajes o no están en el formato correcto\"}");
            return;
        }

        ArrayList<Personaje> personajes = new ArrayList<>();
        personajesNode.forEach(node -> {
            String name = node.get("name").asText("");
            String role = node.get("role").asText("");
            int salud = node.get("salud").asInt(0);
            int speed = node.get("velocidad").asInt(0);
            int defense = node.get("defensa").asInt(0);
            String image = node.get("image").asText("");

            personajes.add(new Personaje(name, convertirStringARol(role), defense, speed, salud, image));
        });

        for (int i = 0; i < personajes.size(); i++) {
            System.out.println(personajes.get(i).toString());

        }

        request.getSession().setAttribute("selectedPlayers", personajes);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Devolver una respuesta con los personajes procesados
        response.getWriter().write(mapper.writeValueAsString(personajes));
    }

    public Rol convertirStringARol(String rolString) {
        switch (rolString.toUpperCase()) { // Convertir a mayúsculas para evitar problemas con el caso
            case "GUERRERO":
                return Rol.GUERRERO;
            case "MAGO":
                return Rol.MAGO;
            case "ARQUERO":
                return Rol.ARQUERO;
            default:
                throw new IllegalArgumentException("Rol desconocido: " + rolString);
        }
    }
}