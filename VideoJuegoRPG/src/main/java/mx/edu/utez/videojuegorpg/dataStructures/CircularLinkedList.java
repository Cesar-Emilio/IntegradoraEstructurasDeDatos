package mx.edu.utez.videojuegorpg.dataStructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList<T> implements Iterable<T> {

    // Atributos de la lista enlazada circular
    private Node<T> head; // Primer nodo de la lista
    private Node<T> tail; // Último nodo de la lista (que apunta a head)
    private int size;     // Tamaño de la lista
    private Node<T> current; // Nodo actual (para gestionar el turno)

    // Constructor
    public CircularLinkedList() {
        head = null;
        tail = null;
        size = 0;
        current = null; // Inicializamos el nodo actual como null
    }

    // Método para añadir un elemento al final de la lista
    public boolean add(T element) {
        Node<T> newNode = new Node<>(element);

        if (head == null) { // Si la lista está vacía
            head = newNode;
            tail = newNode;
            tail.next = head; // Cerrar el ciclo, apuntando al head
            current = head;   // El primer nodo es el actual
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head; // Mantener la circularidad
        }

        size++;
        return true;
    }

    public static <T> CircularLinkedList<T> of(T... elements) {
        CircularLinkedList<T> list = new CircularLinkedList<>();
        for (T element : elements) {
            list.add(element);
        }
        return list;
    }

    // Método para obtener un elemento por índice
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "El índice está fuera de los límites de la lista"
            );
        }

        Node<T> currentNode = head;
        // Recorrer hasta el índice deseado
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }

        return currentNode.data;
    }

    // Método para obtener el nodo actual
    public T getCurrent() {
        if (current == null) {
            return null; // La lista está vacía
        }
        return current.data;
    }

    // Método para avanzar al siguiente nodo (simula el paso de turno)
    public void advanceCurrent() {
        if (current != null) {
            current = current.next; // Avanzamos al siguiente nodo
        }
    }

    // Método para remover un elemento por índice
    public boolean remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "El índice está fuera de los límites de la lista"
            );
        }

        if (index == 0) {
            if (size == 1) {
                // Si solo hay un nodo, eliminarlo y reiniciar la lista
                head = null;
                tail = null;
                current = null; // La lista está vacía
            } else {
                head = head.next; // Mover la cabeza al siguiente nodo
                tail.next = head; // Mantener la circularidad
                current = head;   // El nuevo nodo inicial es el actual
            }
        } else {
            Node<T> currentNode = head;
            // Buscar el nodo anterior al que queremos eliminar
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.next;
            }

            // Saltar el nodo a eliminar
            currentNode.next = currentNode.next.next;

            // Si el nodo a eliminar es el último, actualizar tail
            if (index == size - 1) {
                tail = currentNode;
            }
        }

        size--;
        return true;
    }

    // Método para obtener el tamaño de la lista
    public int size() {
        return size;
    }

    // Método para verificar si la lista está vacía
    public boolean isEmpty() {
        return size == 0;
    }

    public Node<T> getHead() {
        return head;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = currentNode.data;
                currentNode = currentNode.next;
                return data;
            }
        };
    }

}
