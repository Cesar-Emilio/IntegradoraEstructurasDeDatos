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
import mx.edu.utez.videojuegorpg.model.Personaje;

import java.io.IOException;

@WebServlet("/IniciarBatallaServlet")
public class IniciarBatallaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Personaje> personajesAliados = new ArrayList<>();
        ArrayList<Enemigo> personajesEnemigos = new ArrayList<>();
        int nivelSeleccionado;

        try {
            JsonNode rootNode = mapper.readTree(request.getReader());

            JsonNode nivelNode = rootNode.get("nivelActual");
            if (nivelNode == null || !nivelNode.isInt()) {
                throw new IllegalArgumentException("El nivel seleccionado es inválido o está ausente");
            }
            nivelSeleccionado = nivelNode.asInt();

            JsonNode personajesNode = rootNode.get("personajes");
            if (personajesNode != null && personajesNode.isArray()) {
                personajesNode.forEach(node -> {
                    Personaje personaje = mapper.convertValue(node, Personaje.class);
                    personajesAliados.add(personaje);
                });
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Datos enviados son inválidos\"}");
            return;
        }

        // TODO: Inicializar enemigos para el nivel seleccionado
        // personajesEnemigos = inicializarEnemigos(nivelSeleccionado);

        request.setAttribute("personajes", personajesAliados);
        request.setAttribute("enemigos", personajesEnemigos);

        request.getRequestDispatcher("batalla.jsp").forward(request, response);
    }
}
