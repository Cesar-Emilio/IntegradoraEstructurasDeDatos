package mx.edu.utez.videojuegorpg.enums;

import mx.edu.utez.videojuegorpg.dataStructures.ArrayList;
import mx.edu.utez.videojuegorpg.model.Habilidad;

public enum Rol {
    GUERRERO(
        new Habilidad[] {
            new Habilidad("Golpe Poderoso", 30),
            new Habilidad("Golpe Critico", 50),
            new Habilidad("Furia de Batalla", 70),
            new Habilidad("Ráfaga de Espadas", 100, false)
        }
    ),
    MAGO(
        new Habilidad[] {
            new Habilidad("Bola de Fuego", 20),
            new Habilidad("Disparo de Agua", 50),
            new Habilidad("Tormenta Eléctrica", 60),
            new Habilidad("Explosión Final", 120, false)
        }
    ),
    ARQUERO(
        new Habilidad[] {
            new Habilidad("Flecha Precisa", 40),
            new Habilidad("Flecha de Fuego", 50),
            new Habilidad("Disparo Triple", 80),
            new Habilidad("Lluvia de Flechas", 90, false)
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