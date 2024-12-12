package mx.edu.utez.videojuegorpg.util;

import mx.edu.utez.videojuegorpg.model.Enemigo;
import mx.edu.utez.videojuegorpg.model.Habilidad;
import mx.edu.utez.videojuegorpg.model.Personaje;

public class Batalla{
    public void calcularDañoEnemigo(Habilidad habilidad, Enemigo enemigo) {
        enemigo.setSalud((habilidad.getDaño() * 10)/(5 + enemigo.getDefensa()));

    }

    public void calcularDañoAliado(Habilidad habilidad, Personaje aliado) {
        aliado.setSalud((habilidad.getDaño() * 10)/(5 + aliado.getDefensa()));

    }
}
