package mx.edu.utez.videojuegorpg.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.videojuegorpg.enums.Rol;
import mx.edu.utez.videojuegorpg.model.*;

import java.io.IOException;

public class InicioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Equipo<Personaje> miEquipo = new Equipo<>();

        //Personajes de prueba
        Personaje personaje1 = new Personaje("Aragorn", Rol.GUERRERO, 80, 10, 100);
        Personaje personaje2 = new Personaje("Gandalf", Rol.MAGO, 60, 5, 80);

        miEquipo.agregar(personaje1);
        miEquipo.agregar(personaje2);

        request.setAttribute("miEquipo", miEquipo);
        request.getRequestDispatcher("equipo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}