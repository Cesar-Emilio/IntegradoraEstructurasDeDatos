package mx.edu.utez.videojuegorpg.dataStructures;//Pila (Clase) que usa lista enlazada simple
import java.util.EmptyStackException;
import java.util.Iterator;

public class Stack<E> implements Iterable<E> {

    private static class Node<E>{
        private E data;
        private Node<E> next;

        public Node(E data){
            this.data = data;
            this.next = null;
        }
    }

    //atributos
    private Node<E> top;
    private int size;

    //constructor
    public Stack(){
        top = null;
        size = 0;
    }

    //métodos
    public void push(E data){
        Node<E> newNode = new Node<>(data);
        newNode.next = top; 
        top = newNode;
        size++;
    }


    public E peek(){
        if(size==0){
            throw new IllegalStateException("lista vacia");
        }
        return top.data;
    }

    public E pop(){
        if(top == null){
            throw new EmptyStackException();
        }
        E data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public boolean isEmpty(){
        return top == null;
    }

    public int getSize(){
        return size;
    }

    // Implementación del método iterator()
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = top;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new EmptyStackException();
                }
                E data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}