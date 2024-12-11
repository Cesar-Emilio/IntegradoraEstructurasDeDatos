<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Battle Arena</title>
    <link rel="stylesheet" href="css/batalla.css">
</head>
<body>
<div class="battle-container">
    <h1 class="battle-header">Battle Arena</h1>

    <div class="battle-arena">
        <div class="player-cards">
            <!-- Cartas aliadas -->
            <div class="card" id="playerCard1"></div>
            <div class="card" id="playerCard2"></div>
            <div class="card" id="playerCard3"></div>
            <div class="card" id="playerCard4"></div>
            <div class="card" id="playerCard5"></div>
        </div>

        <div class="versus" id="versusText">VS</div>

        <div class="enemy-cards">
            <!-- Cartas enemigas -->
            <div class="card" id="enemyCard1"></div>
            <div class="card" id="enemyCard2"></div>
            <div class="card" id="enemyCard3"></div>
            <div class="card" id="enemyCard4"></div>
            <div class="card" id="enemyCard5"></div>
        </div>
    </div>
</div>
<script src="js/batalla.js"></script>
</body>
</html>