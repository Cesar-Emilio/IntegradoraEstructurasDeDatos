package mx.edu.utez.videojuegorpg.model;

public class Equipo<T extends Personaje> {
    private Nodo<T> cabeza;
    private int tamanio;

    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        public Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    public Equipo() {
        this.cabeza = null;
        this.tamanio = 0;
    }

    public void agregar(T personaje) {
        Nodo<T> nuevo = new Nodo<>(personaje);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        tamanio++;
    }

    public boolean quitar(T personaje) {
        if (cabeza == null) return false;

        if (cabeza.dato.equals(personaje)) {
            cabeza = cabeza.siguiente;
            tamanio--;
            return true;
        }

        Nodo<T> actual = cabeza;
        while (actual.siguiente != null && !actual.siguiente.dato.equals(personaje)) {
            actual = actual.siguiente;
        }

        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente;
            tamanio--;
            return true;
        }
        return false;
    }

    public boolean cambiarPosicion(int indice1, int indice2) {
        if (indice1 < 0 || indice2 < 0 || indice1 >= tamanio || indice2 >= tamanio) return false;

        if (indice1 == indice2) return true;

        Nodo<T> nodo1 = obtenerNodoPorIndice(indice1);
        Nodo<T> nodo2 = obtenerNodoPorIndice(indice2);

        if (nodo1 == null || nodo2 == null) return false;

        T temp = nodo1.dato;
        nodo1.dato = nodo2.dato;
        nodo2.dato = temp;
        return true;
    }

    public void reducirTamanio(int nuevoTamanio) {
        if (nuevoTamanio < 0 || nuevoTamanio >= tamanio) return;

        Nodo<T> actual = cabeza;
        for (int i = 1; i < nuevoTamanio; i++) {
            actual = actual.siguiente;
        }

        actual.siguiente = null;
        tamanio = nuevoTamanio;
    }

    public T obtenerPorIndice(int indice) {
        Nodo<T> nodo = obtenerNodoPorIndice(indice);
        return nodo == null ? null : nodo.dato;
    }

    private Nodo<T> obtenerNodoPorIndice(int indice) {
        if (indice < 0 || indice >= tamanio) return null;

        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        return actual;
    }

    public boolean estaVacio() {
        return tamanio == 0;
    }

    public int obtenerTamanio() {
        return tamanio;
    }

    public void imprimirEquipo() {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            System.out.println(actual.dato);
            actual = actual.siguiente;
        }
    }
}