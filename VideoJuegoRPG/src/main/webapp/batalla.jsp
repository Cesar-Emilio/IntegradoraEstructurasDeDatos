<%--
  Created by IntelliJ IDEA.
  User: cesar
  Date: 07/12/2024
  Time: 08:43 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RPG Layout</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }

        .game-container {
            position: relative;
            width: 100%;
            height: 100vh;
            background-image: url('https://64.media.tumblr.com/00ec803404f6e9d6583f92d8870b5fb8/tumblr_p7k8f3fMWS1wvbydeo1_1280.png');
            background-size: cover;
            background-position: center;
            border: 2px solid #000;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }

        .characters-container {
            position: relative;
            display: flex;
            justify-content: space-between;
            width: 80%;
            height: 50%;
        }

        .contrincantes,
        .personajes {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
            justify-content: center;
            align-items: center;
            width: 30%;
        }

        .character {
            width: 100px;
            height: 100px;
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            border: none;
            cursor: pointer;
        }

        .options {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 10px;
            margin-top: 20px;
            width: 30%;
        }

        .opt {
            width: 100px;
            height: 50px;
            display: flex;
            justify-content: center;
            align-items: center;
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            border: none;
            cursor: pointer;
        }

        .details {
            width: 200px;
            height: 100px;
            background-color: #eee;
            border: 1px solid #000;
            padding: 10px;
            margin-top: 20px;
            position: absolute;
            right: 10%;
            bottom: 10%;
            overflow-y: auto;
        }

        .sidebar {
            display: none;
            position: absolute;
            top: 20%;
            right: 5%;
            background-color: rgba(0, 0, 0, 0.8);
            color: white;
            padding: 20px;
            border-radius: 10px;
            width: 200px;
            height: 300px;
            overflow-y: auto;
        }

        .sidebar .item {
            padding: 10px;
            border-bottom: 1px solid #fff;
            cursor: pointer;
        }

    </style>
</head>
<body>
<div class="game-container">

    <div class="characters-container">

        <div class="contrincantes">
            <div class="character" style="background-image: url('images/duki.png');"></div>
            <div class="character" style="background-image: url('images/duki.png');"></div>
            <div class="character" style="background-image: url('images/duki.png');"></div>
            <div class="character" style="background-image: url('images/duki.png');"></div>
            <div class="character" style="background-image: url('images/duki.png');"></div>
        </div>

        <div class="personajes">
            <div class="character" style="background-image: url('images/duki.png');"></div>
            <div class="character" style="background-image: url('images/duki.png');"></div>
            <div class="character" style="background-image: url('images/duki.png');"></div>
            <div class="character" style="background-image: url('images/duki.png');"></div>
            <div class="character" style="background-image: url('images/duki.png');"></div>
        </div>
    </div>

    <div class="options">
        <div class="opt" id="atacar" style="background-image: url('images/attack.png');"></div>
        <div class="opt" id="defender" style="background-image: url('images/defend.png');"></div>
        <div class="opt" id="options" style="background-image: url('images/options.png');"></div>
        <div class="opt" id="NextTurn" style="background-image: url('images/next.png');"></div>
    </div>

    <div class="details" style="background-image: url('images/details.png')">
        character's details
    </div>

    <div class="sidebar" id="sidebar">
        <div class="item">Objeto 1</div>
        <div class="item">Objeto 2</div>
        <div class="item">Objeto 3</div>
    </div>

</div>

</body>
</html>
