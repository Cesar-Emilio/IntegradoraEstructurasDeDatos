package mx.edu.utez.videojuegorpg.model;

import mx.edu.utez.videojuegorpg.dataStructures.CircularLinkedList;
import mx.edu.utez.videojuegorpg.enums.Rol;
import mx.edu.utez.videojuegorpg.enums.RolEnemigo;
import mx.edu.utez.videojuegorpg.enums.TipoEnemigo;

public class Enemigo extends Personaje {
    private RolEnemigo rol;

    public Enemigo(String nombre, RolEnemigo rol, int defensa, int velocidad, int salud, String imagen) {
        super(nombre, defensa, velocidad, salud, imagen);
        this.rol = rol != null ? rol : RolEnemigo.GUERRERO_NORMAL; // Rol predeterminado
        this.habilidades = CircularLinkedList.of(rol.getHabilidades());
    }


    // Constructor con habilidades, ahora con imagen
    public Enemigo(String nombre, RolEnemigo rol, int defensa, int velocidad, int salud, CircularLinkedList<Habilidad> habilidades, String imagen) {
        super(nombre, defensa, velocidad, salud, habilidades, imagen);
        this.rol = rol;
    }

    public RolEnemigo getRolEnemigo() {
        return rol; // Devuelve el rol específico del enemigo
    }



    @Override
    public String toString() {
        return "Enemigo{" +
                "nombre='" + nombre + '\'' +
                ", rol=" + rol +
                ", defensa=" + defensa +
                ", velocidad=" + velocidad +
                ", salud=" + salud +
                ", habilidades=" + habilidades +
                ", imagen='" + imagen + '\'' +
                '}';
    }

    public TipoEnemigo getCategoria() {
        return rol.getCategoria(); // Obtenemos la categoría desde el Rol
    }
}