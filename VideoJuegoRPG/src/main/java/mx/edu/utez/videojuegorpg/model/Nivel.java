package mx.edu.utez.videojuegorpg.model;

import mx.edu.utez.videojuegorpg.dataStructures.ArrayList;

public class Nivel {
    public ArrayList<Enemigo> obtenerEnemigosNivel(int nivel) {
        return EnemigosConfig.obtenerEnemigos(nivel);

    }
}
