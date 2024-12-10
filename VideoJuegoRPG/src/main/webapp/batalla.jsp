<%--
  Created by IntelliJ IDEA.
  User: cesar
  Date: 07/12/2024
  Time: 08:43 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Batalla</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            background-image: url('https://images.squarespace-cdn.com/content/v1/56fe867e55598610f760ae7c/1597756655577-6WQQBOZ2B5BSMS5KBCN5/snow-background-rework-portfolio-edit.png?format=1500w');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            height: 100vh;
        }

        .container {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
            width: 80%;
            margin: auto;
            padding: 20px;
            background: rgba(255, 255, 255, 0.8);
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        .grupo {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
        }

        .detalles {
            border: 1px solid #ccc;
            padding: 10px;
            width: 100%;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="grupo">
        <div>Personaje 1</div>
        <div>Personaje 2</div>
        <div>Personaje 3</div>
        <div>Personaje 4</div>
        <div>Personaje 5</div>


    </div>
    <div class="grupo">
        <div>Detalles del Personaje</div>
    </div>
</div>
</body>
</html>
