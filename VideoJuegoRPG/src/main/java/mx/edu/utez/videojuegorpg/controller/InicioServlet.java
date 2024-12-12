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
            response.getWriter().write("{\"error\":\"El formato del JSON es inválido\"}");
            return;
        }

        ArrayList<String> usuarios = new ArrayList<>();
        JsonNode personajesNode = rootNode.get("personajes");

        if (personajesNode != null && personajesNode.isArray()) {
            personajesNode.forEach(node -> usuarios.add(node.asText()));
        }

        request.setAttribute("usuarios", usuarios);

        request.getRequestDispatcher("inicio.jsp").forward(request, response);
    }
}