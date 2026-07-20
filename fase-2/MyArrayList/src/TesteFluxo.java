public class TesteFluxo {
    public static void main(String[] args) {

        System.out.println("=== TESTANDO A FILA (MyQueue) - FIFO ===");
        MyQueue<String> filaAtendimento = new MyQueue<>();

        // Clientes chegam na fila
        filaAtendimento.enqueue("Ana");
        filaAtendimento.enqueue("Bernardo");
        filaAtendimento.enqueue("Carlos");

        System.out.println("Próximo a ser atendido: " + filaAtendimento.peek()); // Deve ser Ana
        System.out.println("Atendendo: " + filaAtendimento.dequeue());            // Remove Ana
        System.out.println("Próximo agora: " + filaAtendimento.peek());         // Deve ser Bernardo
        System.out.println("Tamanho da fila: " + filaAtendimento.size());       // Deve ser 2

        System.out.println("\n=== TESTANDO A PILHA (MyStack) - LIFO ===");
        MyStack<String> historicoNavegacao = new MyStack<>();

        // Usuário navega por páginas (ações empilhadas)
        historicoNavegacao.push("Home Page");
        historicoNavegacao.push("Página de Produtos");
        historicoNavegacao.push("Carrinho de Compras");

        System.out.println("Página atual (topo): " + historicoNavegacao.peek()); // Deve ser Carrinho
        System.out.println("Usuário clicou em 'Voltar' (Pop): " + historicoNavegacao.pop()); // Remove Carrinho
        System.out.println("Nova página atual: " + historicoNavegacao.peek());   // Deve ser Produtos
        System.out.println("Tamanho do histórico: " + historicoNavegacao.size()); // Deve ser 2
    }
}
