package mx.edu.utez.videojuegorpg.model;

public class Personaje {
    String nombre;
    Rol rol;
    int defensa;
    int velocidad;
    int salud;
    ArrayList<Habilidad> habilidades;

    public Personaje() {
    }

    public Personaje(String nombre, Rol rol, int defensa, int velocidad, int salud) {
        this.nombre = nombre;
        this.rol = rol;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.salud = salud;
        this.habilidades = rol.getHabilidades(rol);

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public ArrayList<Habilidad> getHabilidades() {
        return rol.getHabilidades(rol);
    }

    public void setHabilidades(ArrayList<Habilidad> habilidades) {
        this.habilidades = habilidades;
    }
}