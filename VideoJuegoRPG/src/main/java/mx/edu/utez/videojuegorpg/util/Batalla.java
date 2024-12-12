package mx.edu.utez.videojuegorpg.util;

import mx.edu.utez.videojuegorpg.dataStructures.Queue;
import mx.edu.utez.videojuegorpg.model.Enemigo;
import mx.edu.utez.videojuegorpg.model.Habilidad;
import mx.edu.utez.videojuegorpg.model.Personaje;
import mx.edu.utez.videojuegorpg.dataStructures.ArrayList;

import java.util.Random;

public class Batalla {

    private Queue<Object> colaTurnos = new Queue<>();  // Cola de turnos para personajes y enemigos
    private Random random = new Random();  // Para los ataques aleatorios del enemigo

    public void iniciarBatalla(Personaje[] personajes, Enemigo[] enemigos) {
        // Crear el ArrayList y agregar personajes y enemigos
        ArrayList<Object> turnos = new ArrayList<>();

        // Agregar personajes y enemigos a la lista
        for (Personaje personaje : personajes) {
            turnos.add(personaje);
        }
        for (Enemigo enemigo : enemigos) {
            turnos.add(enemigo);
        }

        // Ordenar la lista por velocidad usando el algoritmo de burbuja
        ordenarPorVelocidad(turnos);

        // Agregar los elementos a la cola de turnos, en orden de velocidad
        for (int i = 0; i < turnos.size(); i++) {
            colaTurnos.offer(turnos.get(i));
        }

        // Comenzar la batalla
        while (!colaTurnos.isEmpty()) {
            Object atacante = colaTurnos.poll();  // El atacante es el primero en la cola

            if (atacante instanceof Personaje) {
                Personaje personaje = (Personaje) atacante;
                // El jugador selecciona un ataque (simulado como "Golpe" por ejemplo)
                Habilidad ataqueJugador = seleccionarAtaque(personaje);  // Suponemos que el jugador selecciona un ataque
                Enemigo enemigo = (Enemigo) colaTurnos.peek();  // El siguiente enemigo en la cola
                realizarAtaque(ataqueJugador, enemigo);

                // Si el enemigo muere, reemplazamos el enemigo muerto
                if (enemigo.getSalud() <= 0) {
                    colaTurnos.poll();  // Eliminar enemigo muerto
                    // Aquí agregaríamos un nuevo enemigo
                    // agregarNuevoEnemigo();  // Implementar este método
                }
            } else if (atacante instanceof Enemigo) {
                Enemigo enemigo = (Enemigo) atacante;
                // Ataque aleatorio del enemigo
                Habilidad ataqueEnemigo = seleccionarAtaqueAleatorio(enemigo);
                Personaje personaje = (Personaje) colaTurnos.peek();  // El siguiente personaje en la cola
                realizarAtaque(ataqueEnemigo, personaje);

                // Si el personaje muere, reemplazamos el personaje muerto
                if (personaje.getSalud() <= 0) {
                    colaTurnos.poll();  // Eliminar personaje muerto
                    // Aquí agregaríamos un nuevo personaje
                    // agregarNuevoPersonaje();  // Implementar este método
                }
            }

            // Si la cola está vacía, la batalla terminó
            if (colaTurnos.isEmpty()) {
                break;
            }
        }
    }

    private void ordenarPorVelocidad(ArrayList<Object> turnos) {
        // Algoritmo de burbuja para ordenar por velocidad
        for (int i = 0; i < turnos.size(); i++) {
            for (int j = 0; j < turnos.size() - i - 1; j++) {
                Object current = turnos.get(j);
                Object next = turnos.get(j + 1);
                int currentVelocidad = obtenerVelocidad(current);
                int nextVelocidad = obtenerVelocidad(next);

                // Si la velocidad de current es menor que la de next, se intercambian
                if (currentVelocidad < nextVelocidad) {
                    turnos.add(j, next);  // Mover next a la posición actual
                    turnos.add(j + 1, current);  // Mover current a la siguiente posición
                }
            }
        }
    }

    private int obtenerVelocidad(Object obj) {
        if (obj instanceof Personaje) {
            return ((Personaje) obj).getVelocidad();
        } else if (obj instanceof Enemigo) {
            return ((Enemigo) obj).getVelocidad();
        }
        return 0;
    }

    private Habilidad seleccionarAtaque(Personaje personaje) {
        // Aquí se puede permitir al jugador seleccionar un ataque
        // Para simplificar, seleccionamos el primer ataque del personaje
        return personaje.getHabilidad("Golpe");  // Reemplazar con el ataque seleccionado por el jugador
    }

    private void realizarAtaque(Habilidad habilidad, Enemigo defensor) {
        // Calculamos el daño
        int daño = calcularDaño(habilidad, defensor);
        defensor.setSalud(defensor.getSalud() - daño);
    }

    private void realizarAtaque(Habilidad habilidad, Personaje defensor) {
        // Calculamos el daño
        int daño = calcularDaño(habilidad, defensor);
        defensor.setSalud(defensor.getSalud() - daño);
    }

    private int calcularDaño(Habilidad habilidad, Object defensor) {
        // El daño se calcula según el tipo de defensor (Personaje o Enemigo)
        if (defensor instanceof Enemigo) {
            return (habilidad.getDaño() * 10) / (5 + ((Enemigo) defensor).getDefensa());
        } else if (defensor instanceof Personaje) {
            return (habilidad.getDaño() * 10) / (5 + ((Personaje) defensor).getDefensa());
        }
        return 0;
    }

    private Habilidad seleccionarAtaqueAleatorio(Enemigo enemigo) {
        // Seleccionamos aleatoriamente un ataque del enemigo
        int index = random.nextInt(enemigo.getHabilidades().size());
        return enemigo.getHabilidades().get(index);
    }

    private void agregarNuevoPersonaje() {
    }

    private void agregarNuevoEnemigo() {
    }
}
