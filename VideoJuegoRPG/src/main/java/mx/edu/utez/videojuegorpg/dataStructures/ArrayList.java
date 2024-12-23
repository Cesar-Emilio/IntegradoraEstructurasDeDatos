package mx.edu.utez.videojuegorpg.dataStructures;

public class ArrayList<T> {
    // Atributos que definen al ArrayList
    private T[] array;
    private int size;
    private int capacity;

    // Constructor de la lista
    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.capacity = 10; // Iniciamos con 10 espacios
        this.array = (T[]) new Object[this.capacity];
        this.size = 0;
    }

    // Método estático para crear una lista desde elementos
    @SafeVarargs
    public static <T> ArrayList<T> of(T... elements) {
        ArrayList<T> list = new ArrayList<>();
        for (T element : elements) {
            list.add(element);
        }
        return list;
    }

    // Redimensionar el array si es necesario
    @SuppressWarnings("unchecked")
    private void resize() {
        this.capacity *= 2;
        T[] newArray = (T[]) new Object[this.capacity];
        // Copiar el array viejo en el nuevo
        System.arraycopy(this.array, 0, newArray, 0, size);
        this.array = newArray;
    }

    // Método para añadir un elemento al final del ArrayList
    public boolean add(T elemento) {
        if (this.size == this.capacity) {
            resize();
        }
        this.array[this.size++] = elemento;
        return true;
    }

    // Método para añadir un elemento en una posición específica
    public void add(int index, T elemento) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Índice fuera de los límites");
        }

        if (this.size == this.capacity) {
            resize();
        }

        // Mover los elementos a la derecha para hacer espacio
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }

        array[index] = elemento;
        size++;
    }

    // Método para obtener un elemento por índice
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("El índice está fuera de los límites del array");
        }
        return this.array[index];
    }

    // Método para remover un elemento por índice
    public boolean remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("El índice está fuera de los límites del array");
        }

        // Mover los elementos después del índice hacia la izquierda
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }

        array[size - 1] = null; // Opcional: limpiar el último elemento
        size--;

        return true;
    }

    // Método para obtener el tamaño del ArrayList
    public int size() {
        return this.size;
    }

    // Método para verificar si el ArrayList está vacío
    public boolean isEmpty() {
        return this.size == 0;
    }

    // Método para limpiar todo el ArrayList
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    // Método para buscar si un elemento está en el ArrayList
    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                return true;
            }
        }
        return false;
    }
}
