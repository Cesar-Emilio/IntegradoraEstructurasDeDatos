package mx.edu.utez.videojuegorpg.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.videojuegorpg.dataStructures.ArrayList;
import mx.edu.utez.videojuegorpg.model.Personaje;

import java.io.IOException;

@WebServlet("/SeleccionNivelServlet")
public class SeleccionNivelServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<String> nombrePersonajes = new ArrayList<>();

        try {
            JsonNode rootNode = mapper.readTree(request.getReader());
            JsonNode personajesNode = rootNode.get("nombrePersonajes");

            if (personajesNode != null && personajesNode.isArray()) {
                personajesNode.forEach(node -> nombrePersonajes.add(node.asText()));
            }
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"El formato del JSON es inválido\"}");
            return;
        }

        Integer nivelMaximo = (Integer) request.getAttribute("nivelMaximo");
        if (nivelMaximo == null) {
            nivelMaximo = 1;
        }

        //TODO: HACER LA LÓGICA PARA OBTENER LOS NOMBRES DE LOS NIVELES
        ArrayList<String> niveles = new ArrayList<>();
        for (int i = 1; i <= nivelMaximo; i++) {
            //TODO: Agregar el nombre del nivel
        }

        ArrayList<Personaje> personajes = new ArrayList<>();
        //TODO: HACER LA LÓGICA PARA OBTENER LOS PERSONAJES
        int cantidadPersonajes = nombrePersonajes.size();
        for (int i = 0; i < cantidadPersonajes; i++) {
            //Obtener los personajes
        }

        request.setAttribute("personajes", personajes);
        request.setAttribute("niveles", niveles);

        request.getRequestDispatcher("seleccionNivel.jsp").forward(request, response);
    }
}