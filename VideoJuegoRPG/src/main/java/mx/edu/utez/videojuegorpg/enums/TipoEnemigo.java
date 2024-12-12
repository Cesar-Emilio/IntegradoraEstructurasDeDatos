package mx.edu.utez.videojuegorpg.enums;

public enum TipoEnemigo {
    NORMAL(1.0),
    ELITE(1.4),
    JEFE(2.0);

    private final double multiplicador;

    TipoEnemigo(double multiplicador) {
        this.multiplicador = multiplicador;
    }

    public double getMultiplicador() {
        return multiplicador;
    }
}
