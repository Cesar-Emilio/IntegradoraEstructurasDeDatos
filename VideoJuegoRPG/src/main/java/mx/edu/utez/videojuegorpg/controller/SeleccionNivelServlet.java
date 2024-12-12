package mx.edu.utez.videojuegorpg.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name="SeleccionServlet",value="/SeleccionNivelServlet")
public class SeleccionNivelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int nivelMaximo = (int) request.getAttribute("nivelMaximo");

        // Generar niveles y enemigos
        Map<Integer, List<String>> nivelesYEnemigos = new HashMap<>();
        for (int i = 1; i <= nivelMaximo; i++) {
            nivelesYEnemigos.put(i, generarEnemigosParaNivel(i));
        }

        // Enviar datos al JSP
        request.setAttribute("nivelesYEnemigos", nivelesYEnemigos);
        request.setAttribute("nivelMaximo", nivelMaximo);

        // Redirige a la vista de selección de nivel
        request.getRequestDispatcher("seleccionNivel.jsp").forward(request, response);
    }

    /**
     * Método para generar enemigos para un nivel.
     * @param nivel Número del nivel.
     * @return Lista con los nombres de los enemigos.
     */
    private List<String> generarEnemigosParaNivel(int nivel) {
        List<String> enemigos = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            enemigos.add("Enemigo " + i + " del Nivel " + nivel);
        }
        return enemigos;
    }
}