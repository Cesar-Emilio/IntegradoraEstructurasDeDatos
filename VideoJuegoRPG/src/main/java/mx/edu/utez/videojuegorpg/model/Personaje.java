package mx.edu.utez.videojuegorpg.model;

import mx.edu.utez.videojuegorpg.dataStructures.ArrayList;
import mx.edu.utez.videojuegorpg.dataStructures.CircularLinkedList;
import mx.edu.utez.videojuegorpg.dataStructures.Node;
import mx.edu.utez.videojuegorpg.enums.Rol;

public class Personaje {
    String nombre;
    Rol rol;
    int defensa;
    int velocidad;
    int salud;
    CircularLinkedList<Habilidad> habilidades;
    String imagen;

    public Personaje() {}

    public Personaje(String nombre, Rol rol, int defensa, int velocidad, int salud, String imagen) {
        this.nombre = nombre;
        this.rol = rol;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.salud = salud;
        this.habilidades = rol.getHabilidades(rol);
        this.imagen = imagen;
    }

    public Personaje(String nombre, int defensa, int velocidad, int salud, String imagen) {
        this.nombre = nombre;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.salud = salud;
        this.imagen = imagen;
    }

    public Personaje(String nombre, int defensa, int velocidad, int salud, CircularLinkedList<Habilidad> habilidades, String imagen) {
        this.nombre = nombre;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.salud = salud;
        this.habilidades = habilidades;
        this.imagen = imagen;
    }

    public Personaje(String nombre, int defensa, int velocidad, int salud, ArrayList<Habilidad> habilidades, String imagen) {
    }

    public Habilidad getHabilidad(String nombre) {
        // Recorremos la lista circular de habilidades
        Node<Habilidad> current = habilidades.getHead();
        do {
            if (current.data.getNombre().equals(nombre)) {
                return current.data;  // Si encontramos la habilidad, la retornamos
            }
            current = current.next;
        } while (current != habilidades.getHead());  // Continuamos hasta que volvamos al inicio de la lista

        return null;  // Si no se encuentra la habilidad, retornamos null
    }

    public CircularLinkedList<Habilidad> getHabilidades() {
        return rol.getHabilidades(rol);
    }

    public void setHabilidades(CircularLinkedList<Habilidad> habilidades) {
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Personaje{" +
                "nombre='" + nombre + '\'' +
                ", rol=" + rol +
                ", defensa=" + defensa +
                ", velocidad=" + velocidad +
                ", salud=" + salud +
                ", habilidades=" + habilidades +
                ", imagen='" + imagen + '\'' +
                '}';
    }

    // Otros métodos
}
