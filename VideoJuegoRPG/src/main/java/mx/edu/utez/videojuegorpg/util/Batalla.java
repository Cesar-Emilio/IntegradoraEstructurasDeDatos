package mx.edu.utez.videojuegorpg.util;

import mx.edu.utez.videojuegorpg.dataStructures.CircularLinkedList;
import mx.edu.utez.videojuegorpg.dataStructures.Queue;
import mx.edu.utez.videojuegorpg.model.Enemigo;
import mx.edu.utez.videojuegorpg.model.Habilidad;
import mx.edu.utez.videojuegorpg.model.Personaje;

import java.util.Random;

public class Batalla {

    public String ejecutarBatalla(Personaje personaje, Enemigo enemigo, Habilidad habilidadSeleccionada) {
        StringBuilder resultado = new StringBuilder();

        // Crear la cola de turnos
        Queue<Object> colaTurnos = new Queue<>();

        // Determinar el orden basado en la velocidad
        if (personaje.getVelocidad() >= enemigo.getVelocidad()) {
            colaTurnos.offer(personaje);
            colaTurnos.offer(enemigo);
        } else {
            colaTurnos.offer(enemigo);
            colaTurnos.offer(personaje);
        }

        // Procesar un turno
        Object atacante = colaTurnos.poll();

        if (atacante instanceof Personaje) {
            int daño = ejecutarAtaque(habilidadSeleccionada, enemigo);
            resultado.append(personaje.getNombre()).append(" (").append(personaje.getSalud()).append(" vida) ")
                    .append("usó ").append(habilidadSeleccionada.getNombre())
                    .append(" y causó ").append(daño).append(" de daño a ")
                    .append(enemigo.getNombre()).append(" (").append(enemigo.getSalud()).append(" vida).\n");
        } else if (atacante instanceof Enemigo) {
            Habilidad habilidadEnemigo = seleccionarHabilidadAleatoria(enemigo);
            int daño = ejecutarAtaque(habilidadEnemigo, personaje);
            resultado.append(enemigo.getNombre()).append(" (").append(enemigo.getSalud()).append(" vida) ")
                    .append("usó ").append(habilidadEnemigo.getNombre())
                    .append(" y causó ").append(daño).append(" de daño a ")
                    .append(personaje.getNombre()).append(" (").append(personaje.getSalud()).append(" vida).\n");
        }

        return resultado.toString();
    }


    private int ejecutarAtaque(Habilidad habilidad, Object defensor) {
        int dañoBase = habilidad.getDaño();
        if (defensor instanceof Enemigo) {
            Enemigo enemigo = (Enemigo) defensor;
            int daño = (dañoBase * 10) / (5 + enemigo.getDefensa());
            enemigo.setSalud(enemigo.getSalud() - daño);
            return daño;
        } else if (defensor instanceof Personaje) {
            Personaje personaje = (Personaje) defensor;
            int daño = (dañoBase * 10) / (5 + personaje.getDefensa());
            personaje.setSalud(personaje.getSalud() - daño);
            return daño;
        }
        return 0;
    }

    private Habilidad seleccionarHabilidadAleatoria(Enemigo enemigo) {
        CircularLinkedList<Habilidad> habilidades = enemigo.getHabilidades();
        if (habilidades == null || habilidades.isEmpty()) {
            // Si no tiene habilidades, usa un ataque básico predeterminado
            return new Habilidad("Ataque Básico", 10);
        }
        Random random = new Random();
        int index = random.nextInt(habilidades.size());
        return habilidades.get(index);
    }

}