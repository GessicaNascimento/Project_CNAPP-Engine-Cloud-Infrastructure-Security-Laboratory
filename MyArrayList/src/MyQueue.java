public class MyQueue<T> {
    private MyLinkedList<T> armazenamento;

    public MyQueue() {
        this.armazenamento = new MyLinkedList<>();
    }

    public void enqueue(T elemento) {
        armazenamento.add(elemento);
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila está vazia!");
        }
        return armazenamento.get(0);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila está vazia!");
        }

        return armazenamento.removeFirst();
    }

    public boolean isEmpty() {
        return armazenamento.size() == 0;
    }

    public int size() {
        return armazenamento.size();
    }
}