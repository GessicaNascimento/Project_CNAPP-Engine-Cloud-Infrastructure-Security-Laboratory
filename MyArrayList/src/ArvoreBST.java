public class ArvoreBST {
    public static void main(String[] args) {
        MyBST<Integer> arvore = new MyBST<>();

        arvore.insert(50); // Raiz
        arvore.insert(30); // Vai para a esquerda de 50
        arvore.insert(70); // Vai para a direita de 50
        arvore.insert(20); // Esquerda de 30
        arvore.insert(40); // Direita de 30
        arvore.insert(60); // Esquerda de 70
        arvore.insert(80); // Direita de 70

        System.out.println("=== TESTE DE BUSCA NA ÁRVORE ===");
        System.out.println("A árvore contém o número 40? " + arvore.contains(40)); // Deve ser true
        System.out.println("A árvore contém o número 90? " + arvore.contains(90)); // Deve ser false
        System.out.println("Tamanho total da árvore: " + arvore.size());          // Deve ser 7
    }
}