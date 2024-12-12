package mx.edu.utez.videojuegorpg.enums;

import mx.edu.utez.videojuegorpg.model.Habilidad;

public enum RolEnemigo {
    // GUERRERO
    GUERRERO_NORMAL(
            TipoEnemigo.NORMAL,
            new Habilidad[] {
                    new Habilidad("Golpe Débil", 20),
                    new Habilidad("Defensa Rápida", 10)
            }
    ),
    GUERRERO_ELITE(
            TipoEnemigo.ELITE,
            new Habilidad[] {
                    new Habilidad("Ataque Fuerte", 25),
                    new Habilidad("Escudo de Hierro", 15),
                    new Habilidad("Hiper papeo", 35)
            }
    ),
    GUERRERO_JEFE(
            TipoEnemigo.JEFE,
            new Habilidad[] {
                    new Habilidad("Rugido de Guerra", 40),
                    new Habilidad("Impacto Devastador", 50),
                    new Habilidad("Falló...", 15),
                    new Habilidad("Skibidi rizz", 100)
            }
    ),

    // MAGO
    MAGO_NORMAL(
            TipoEnemigo.NORMAL,
            new Habilidad[] {
                    new Habilidad("Golpe sombra", 25),
                    new Habilidad("Jalón de patas", 15)
            }
    ),
    MAGO_ELITE(
            TipoEnemigo.ELITE,
            new Habilidad[] {
                    new Habilidad("Bola oscura", 30),
                    new Habilidad("Levantón", 20),
                    new Habilidad("Roast", 35)
            }
    ),
    MAGO_JEFE(
            TipoEnemigo.JEFE,
            new Habilidad[] {
                    new Habilidad("Impacto de la luz lunar", 45),
                    new Habilidad("Secuestro", 70),
                    new Habilidad("Falló...", 5),
                    new Habilidad("Funada en twitter", 110)
            }
    );

    private final TipoEnemigo categoria;
    private final Habilidad[] habilidades;

    RolEnemigo(TipoEnemigo categoria, Habilidad[] habilidades) {
        this.categoria = categoria;
        this.habilidades = habilidades;
    }

    public TipoEnemigo getCategoria() {
        return categoria;
    }

    public Habilidad[] getHabilidades() {
        return habilidades;
    }
}
