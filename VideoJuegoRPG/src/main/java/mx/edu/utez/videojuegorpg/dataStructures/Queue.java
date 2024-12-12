package mx.edu.utez.videojuegorpg.dataStructures;

// Clase cola que implementa Nodos Enlazados
public class Queue <E> {

    // Clase nodo interna
    private static class Node<E> {
        public E data;
        public Node<E> next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    // Atributos de clase
    private Node<E> front;
    private Node<E> rear;
    private int size;

    // Métodos de clase
    // Stack = push | List = add | Cola = offer
    public boolean offer(E data) {
        Node<E> newNode = new Node<>(data);
        if (rear != null) {
            rear.next = newNode;
        }
        rear = newNode;
        if (front == null) {
            front = rear;
        }
        size++;
        return true;
    }

    // Stack = peek | List = get | Cola = peek
    public E peek() {
        return (front == null) ? null : front.data;
    }

    // Stack = pop | List = remove | Cola = poll
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return data;
    }

    // isEmpty
    public boolean isEmpty() {
        return size == 0;
    }

    // size
    public int size() {
        return size;
    }
}
