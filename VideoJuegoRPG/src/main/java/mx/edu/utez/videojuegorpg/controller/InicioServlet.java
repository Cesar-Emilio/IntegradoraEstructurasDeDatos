package mx.edu.utez.videojuegorpg.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.videojuegorpg.model.*;

import java.io.IOException;

public class InicioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Equipo<Personaje> miEquipo = new Equipo<>();

        Rol guerreroRol = new Rol("Guerrero", new ArrayList<Habilidad>() {{
            add(new Habilidad("Ataque Básico", 10));
            add(new Habilidad("Habilidad Especial", 20));
        }});

        Rol magoRol = new Rol("Mago", new ArrayList<Habilidad>() {{
            add(new Habilidad("Bola de Fuego", 15));
            add(new Habilidad("Escudo Mágico", 10));
        }});

        Personaje personaje1 = new Personaje("Aragorn", guerreroRol, 80, 10, 100);
        Personaje personaje2 = new Personaje("Gandalf", magoRol, 60, 5, 80);

        miEquipo.agregar(personaje1);
        miEquipo.agregar(personaje2);

        request.setAttribute("miEquipo", miEquipo);
        request.getRequestDispatcher("equipo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}