package mx.edu.utez.videojuegorpg.enums;

import mx.edu.utez.videojuegorpg.model.ArrayList;
import mx.edu.utez.videojuegorpg.model.Habilidad;

public enum Rol {
    GUERRERO(
        new Habilidad[] {
            new Habilidad("Golpe Poderoso", 30),
            new Habilidad("Defensa de Acero", 0),
            new Habilidad("Furia de Batalla", 40),
            new Habilidad("Ráfaga de Espadas", 50, false)
        }
    ),
    MAGO(
        new Habilidad[] {
            new Habilidad("Bola de Fuego", 25),
            new Habilidad("Escudo Arcano", 0),
            new Habilidad("Tormenta Eléctrica", 35),
            new Habilidad("Explosión Final", 60, false)
        }
    ),
    ARQUERO(
        new Habilidad[] {
            new Habilidad("Flecha Precisa", 20),
            new Habilidad("Red de Trampa", 0),
            new Habilidad("Disparo Triple", 30),
            new Habilidad("Lluvia de Flechas", 45, false)
        }
    );

    private final ArrayList<Habilidad> habilidades = new ArrayList<>();

    Rol(Habilidad[] habilidades) {
        for (Habilidad habilidad : habilidades) {
            this.habilidades.add(habilidad);
        }
    }

    public ArrayList<Habilidad> getHabilidades(Rol rol) {
        switch (rol) {
            case GUERRERO:
                return Rol.GUERRERO.habilidades;
            case MAGO:
                return Rol.MAGO.habilidades;
            case ARQUERO:
                return Rol.ARQUERO.habilidades;
        }
        return null;
    }
}