package mx.edu.utez.videojuegorpg.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/AccionServlet")
public class AccionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String accion;
        String personajeNombre;
        String enemigoNombre = null;
        String habilidadNombre = null;

        try {
            JsonNode rootNode = mapper.readTree(request.getReader());

            JsonNode accionNode = rootNode.get("Accion");
            JsonNode personajeNode = rootNode.get("Personaje");

            if (accionNode == null || personajeNode == null) {
                throw new IllegalArgumentException("Accion o Personaje no proporcionados");
            }

            accion = accionNode.asText();
            personajeNombre = personajeNode.asText();

            JsonNode enemigoNode = rootNode.get("Enemigo");
            if (enemigoNode != null) {
                enemigoNombre = enemigoNode.asText();
            }

            JsonNode habilidadNode = rootNode.get("Habilidad");
            if (habilidadNode != null) {
                habilidadNombre = habilidadNode.asText();
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Datos enviados son inválidos\"}");
            return;
        }

        switch (accion.toLowerCase()) {
            case "ataque":
                // TODO: Implementar lógica de ataque
                // - Buscar el personaje y la habilidad
                // - Aplicar el daño al enemigo
                break;
            case "defensa":
                // TODO: Implementar lógica de defensa
                // - Incrementar defensa temporal del personaje
                break;
            case "huir":
                // TODO: Implementar lógica para huir
                // - Marcar la batalla como perdida o salir de ella
                break;
            default:
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\":\"Accion no reconocida\"}");
                return;
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("{\"message\":\"Accion procesada exitosamente\"}");
    }
}
