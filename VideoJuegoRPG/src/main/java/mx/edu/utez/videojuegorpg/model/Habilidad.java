package mx.edu.utez.videojuegorpg.model;

public class Habilidad {
    private final String nombre;
    private final int daño;
    boolean fueUsada;

    public Habilidad(String nombre, int daño) {
        this.nombre = nombre;
        this.daño = daño;
    }

    public Habilidad(String nombre, int daño, boolean fueUsada) {
        this.nombre = nombre;
        this.daño = daño;
        this.fueUsada = fueUsada;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDaño() {
        return daño;
    }

    public boolean isFueUsada() {
        return fueUsada;
    }

    public void setFueUsada(boolean fueUsada) {
        this.fueUsada = fueUsada;
    }
}