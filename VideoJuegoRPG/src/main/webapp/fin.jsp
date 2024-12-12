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
    <%
        boolean ganador = Boolean.parseBoolean(request.getParameter("ganador"));
    %>
    <h1>
        <%= ganador ? "¡Ganaste!" : "Perdiste" %>
    </h1>
    <button id="inicio-btn" onclick="window.location.href='inicio.jsp'">
        Volver al Inicio
    </button>
</div>
</body>
</html>