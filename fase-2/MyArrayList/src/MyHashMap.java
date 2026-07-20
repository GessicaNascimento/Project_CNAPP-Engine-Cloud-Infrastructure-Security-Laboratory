public class MyHashMap<K, V> {

    private static class Node<K, V> {
        K chave;
        V valor;
        Node<K, V> proximo;

        Node(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
            this.proximo = null;
        }
    }

    private Node<K, V>[] tabela;
    private int capacidade;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        this.capacidade = 10;
        this.tabela = new Node[capacidade];
    }


    private int calcularIndice(K chave) {
        if (chave == null) {
            return 0;
        }

        int hash = chave.hashCode();
        return Math.abs(hash) % capacidade;
    }

    public void put(K chave, V valor) {
        int indice = calcularIndice(chave);
        Node<K, V> novoNo = new Node<>(chave, valor);

        if (tabela[indice] == null) {
            tabela[indice] = novoNo;
        } else {
            Node<K, V> atual = tabela[indice];
            while (true) {

                if (atual.chave.equals(chave)) {
                    atual.valor = valor;
                    return;
                }

                if (atual.proximo == null) {
                    atual.proximo = novoNo;
                    return;
                }
                atual = atual.proximo;
            }
        }
    }

    public V get(K chave) {
        int indice = calcularIndice(chave);
        Node<K, V> atual = tabela[indice];


        while (atual != null) {
            if (atual.chave.equals(chave)) {
                return atual.valor;
            }
            atual = atual.proximo;
        }

        return null;
    }
}