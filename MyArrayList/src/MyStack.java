public class MyStack<T> {
    private MyLinkedList<T> armazenamento;

    public MyStack() {
        this.armazenamento = new MyLinkedList<>();
    }

    public void push(T elemento) {
        armazenamento.add(elemento);
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("A pilha está vazia!");
        }

        return armazenamento.get(armazenamento.size() - 1);
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("A pilha está vazia!");
        }

        return armazenamento.removeLast();
    }

    public boolean isEmpty() {
        return armazenamento.size() == 0;
    }

    public int size() {
        return armazenamento.size();
    }
}


