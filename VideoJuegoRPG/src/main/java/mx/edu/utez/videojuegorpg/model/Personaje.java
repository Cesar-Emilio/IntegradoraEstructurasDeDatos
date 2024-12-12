package mx.edu.utez.videojuegorpg.model;

import mx.edu.utez.videojuegorpg.dataStructures.ArrayList;
import mx.edu.utez.videojuegorpg.enums.Rol;

import java.util.Random;

public class Personaje {
    String nombre;
    Rol rol;
    int defensa;
    int velocidad;
    int salud;
    ArrayList<Habilidad> habilidades;

    public Personaje() {
    }

    public Personaje(String nombre, Rol rol) {
        this.nombre = nombre;
        this.rol = rol;
        Random random = new Random();
        this.defensa = random.nextInt(50) + 20;
        this.velocidad = random.nextInt(100) + 50;
        this.salud = random.nextInt(200) + 80;
        this.habilidades = rol.getHabilidades(rol);

    }

    public Personaje(String nombre, int defensa, int velocidad, int salud) {
        this.nombre = nombre;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.salud = salud;
    }

    public Personaje(String nombre, int defensa, int velocidad, int salud, ArrayList<Habilidad> habilidades) {
        this.nombre = nombre;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.salud = salud;
        this.habilidades = habilidades;
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

    public Personaje getPersonaje(String nombre) {
        if (this.nombre.equals(nombre)) {
            return this;
        }
        return null;
    }

    public Habilidad getHabilidad(String nombre) {
        for (int i = 0; i < rol.getHabilidades(rol).size(); i++) {
            if (rol.getHabilidades(rol).get(i).getNombre().equals(nombre)) {
                return rol.getHabilidades(rol).get(i);
            }
        }
        return null;
    }
}