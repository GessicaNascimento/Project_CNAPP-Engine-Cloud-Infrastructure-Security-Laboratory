public class MyLinkedList<T> {

    private static class Node<T> {
        T valor;
        Node<T> proximo;

        Node(T valor) {
            this.valor = valor;
            this.proximo = null;
        }
    }

    private Node<T> head;
    private int tamanho;

    public MyLinkedList() {
        this.head = null;
        this.tamanho = 0;
    }

    public void add(T elemento) {
        Node<T> novoNo = new Node<>(elemento);

        if (head == null) {
            head = novoNo;
        } else {
            Node<T> atual = head;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novoNo;
        }
        tamanho++;
    }

    public T get(int indice) {
        if (indice < 0 || indice >= tamanho) {
            throw new IndexOutOfBoundsException("Índice inexistente!");
        }

        Node<T> atual = head;
        for (int i = 0; i < indice; i++) {
            atual = atual.proximo;
        }
        return atual.valor;
    }

    public int size() {
        return this.tamanho;
    }


    public T removeFirst() {
        if (head == null) {
            throw new IllegalStateException("A estrutura está vazia!");
        }

        T valorRemovido = head.valor;
        head = head.proximo;
        tamanho--;

        return valorRemovido;
    }

    public T removeLast() {
        if (head == null) {
            throw new IllegalStateException("A estrutura está vazia!");
        }

        T valorRemovido;

        if (head.proximo == null) {
            valorRemovido = head.valor;
            head = null;
        } else {

            Node<T> atual = head;
            while (atual.proximo.proximo != null) {
                atual = atual.proximo;
            }

            valorRemovido = atual.proximo.valor;
            atual.proximo = null;
        }

        tamanho--;
        return valorRemovido;
    }
}