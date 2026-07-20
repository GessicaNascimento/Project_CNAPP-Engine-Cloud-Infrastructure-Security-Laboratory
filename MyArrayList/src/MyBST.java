public class MyBST<T extends Comparable<T>> {

    private static class TreeNode<T> {
        T valor;
        TreeNode<T> esquerda;
        TreeNode<T> direita;

        TreeNode(T valor) {
            this.valor = valor;
            this.esquerda = null;
            this.direita = null;
        }
    }

    private TreeNode<T> raiz;
    private int tamanho;

    public MyBST() {
        this.raiz = null;
        this.tamanho = 0;
    }

    public void insert(T valor) {
        raiz = inserirRecursivo(raiz, valor);
        tamanho++;
    }

    private TreeNode<T> inserirRecursivo(TreeNode<T> atual, T valor) {
        if (atual == null) {
            return new TreeNode<>(valor);
        }

        int comparacao = valor.compareTo(atual.valor);

        if (comparacao < 0) {
            atual.esquerda = inserirRecursivo(atual.esquerda, valor);
        } else if (comparacao > 0) {
            atual.direita = inserirRecursivo(atual.direita, valor);
        }

        return atual;
    }

    public boolean contains(T valor) {
        return buscarRecursivo(raiz, valor);
    }

    private boolean buscarRecursivo(TreeNode<T> atual, T valor) {
        if (atual == null) {
            return false;
        }

        int comparacao = valor.compareTo(atual.valor);

        if (comparacao == 0) {
            return true;
        }

        return comparacao < 0
                ? buscarRecursivo(atual.esquerda, valor)
                : buscarRecursivo(atual.direita, valor);
    }

    public int size() {
        return this.tamanho;
    }
}