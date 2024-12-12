<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="mx.edu.utez.videojuegorpg.model.Personaje" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Selección de Nivel</title>
    <link rel="stylesheet" type="text/css" href="css/niveles.css">
</head>
<body>
<div class="menu-container">
    <h1>Selecciona tu Nivel</h1>
    <form action="JuegoServlet" method="POST">
        <!-- Selector de Niveles -->
        <div class="select-container">
            <label for="nivel">Nivel:</label>
            <select name="nivel" id="nivel">
                <%
                    // Verificar y castear el atributo "niveles"
                    List<String> niveles = (List<String>) request.getAttribute("niveles");
                    if (niveles != null && !niveles.isEmpty()) {
                        for (String nivel : niveles) {
                %>
                <option value="<%=nivel%>">Nivel <%=nivel%></option>
                <%
                    }
                } else {
                %>
                <option value="">No hay niveles disponibles</option>
                <%
                    }
                %>
            </select>
        </div>

        <!-- Selector de Personajes -->
        <div class="select-container">
            <label for="personaje">Personaje:</label>
            <select name="personaje" id="personaje">
                <%
                    // Verificar y castear el atributo "personajes"
                    List<Personaje> personajes = (List<Personaje>) request.getAttribute("personajes");
                    if (personajes != null && !personajes.isEmpty()) {
                        for (Personaje personaje : personajes) {
                %>
                <option value="<%=personaje.getNombre()%>"><%=personaje.getNombre()%></option>
                <%
                    }
                } else {
                %>

                <option value="">No hay personajes disponibles</option>
                <%
                    }
                %>
            </select>
        </div>

        <button type="submit" id="start-button">Comenzar</button>
    </form>
</div>
</body>
</html>
