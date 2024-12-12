package mx.edu.utez.videojuegorpg.model;

import mx.edu.utez.videojuegorpg.dataStructures.ArrayList;
import mx.edu.utez.videojuegorpg.dataStructures.CircularLinkedList;
import mx.edu.utez.videojuegorpg.enums.RolEnemigo;
import mx.edu.utez.videojuegorpg.enums.TipoEnemigo;

public class Enemigo extends Personaje {
    private RolEnemigo rol;

    public Enemigo(String nombre, RolEnemigo rol, int defensa, int velocidad, int salud, String imagen) {
        super(nombre, defensa, velocidad, salud, imagen);
        this.rol = rol;
    }

    // Constructor con habilidades, ahora con imagen
    public Enemigo(String nombre, RolEnemigo rol, int defensa, int velocidad, int salud, CircularLinkedList<Habilidad> habilidades, String imagen) {
        super(nombre, defensa, velocidad, salud, habilidades, imagen);
        this.rol = rol;
    }

    public TipoEnemigo getCategoria() {
        return rol.getCategoria(); // Obtenemos la categoría desde el Rol
    }
}