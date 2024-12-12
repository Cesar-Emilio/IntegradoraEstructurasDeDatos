package mx.edu.utez.videojuegorpg.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ResultadoServlet", value = "/ResultadoServlet")
public class ResultadoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean nivelGanado = Boolean.parseBoolean(request.getParameter("nivelGanado"));
        int nivelActual = Integer.parseInt(request.getParameter("nivelActual"));

        if (nivelGanado) {
            int nivelMaximo = nivelActual + 1;
            request.setAttribute("mensaje", "¡Felicidades! Has ganado el nivel " + nivelActual + ".");
            request.setAttribute("nivelMaximo", nivelMaximo);
        } else {
            request.setAttribute("mensaje", "Has perdido en el nivel " + nivelActual + ". ¡Inténtalo de nuevo!");
        }

        request.getRequestDispatcher("resultado.jsp").forward(request, response);
    }
}