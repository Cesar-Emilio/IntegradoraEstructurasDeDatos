package mx.edu.utez.videojuegorpg.model;

import mx.edu.utez.videojuegorpg.enums.Rol;
import mx.edu.utez.videojuegorpg.enums.RolEnemigo;
import mx.edu.utez.videojuegorpg.enums.TipoEnemigo;

public class Enemigo extends Personaje {
    private RolEnemigo rol;

    public Enemigo(String nombre, RolEnemigo rol, int defensa, int velocidad, int salud, ArrayList<Habilidad> habilidades) {
        super(nombre, defensa, velocidad, salud, habilidades);
        this.rol = rol;
    }

    public TipoEnemigo getCategoria() {
        return rol.getCategoria(); // Obtenemos la categoría desde el Rol
    }
}