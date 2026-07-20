public class MyArrayList<T> {
    private Object[] elementos;
    private int tamanho;

    public MyArrayList() {
        this.elementos = new Object[10];
        this.tamanho = 0;
    }

    public void add(T elemento) {
        if (tamanho == elementos.length) {
            redimensionar();
        }
        elementos[tamanho] = elemento;
        tamanho++;
    }

    private void redimensionar() {
        int novaCapacidade = elementos.length * 2;
        Object[] novoArray = new Object[novaCapacidade];

        for (int i = 0; i < elementos.length; i++) {
            novoArray[i] = elementos[i];
        }

        this.elementos = novoArray;
    }

    @SuppressWarnings("unchecked")
    public T get(int indice) {
        if (indice < 0 || indice >= tamanho) {
            throw new IndexOutOfBoundsException("Índice fora do mapa da estrutura!");
        }
        return (T) elementos[indice];
    }

    public int size() {
        return this.tamanho;
    }
}