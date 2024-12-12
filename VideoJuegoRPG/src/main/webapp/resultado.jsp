<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Resultado del Juego</title>
    <link rel="stylesheet" type="text/css" href="css/fin.css">
</head>
<body>
<div class="container">
    <h1 class="resultado">
        <% if (request.getAttribute("mensaje") != null) { %>
        ${mensaje}
        <% } else { %>
        No se pudo obtener el resultado.
        <% } %>
    </h1>

    <div class="niveles">
        <% if (request.getAttribute("nivelMaximo") != null && request.getAttribute("nivelActual") != null) { %>
        <p id="colorXD">Nivel Récord: <strong>${nivelMaximo}</strong></p>
        <p id="colorXD">Nivel Actual: <strong>${nivelActual}</strong></p>
        <% } %>
    </div>

    <button id="inicio-btn" onclick="window.location.href='inicio.jsp'">
        Volver al Inicio
    </button>
</div>
</body>
</html>
