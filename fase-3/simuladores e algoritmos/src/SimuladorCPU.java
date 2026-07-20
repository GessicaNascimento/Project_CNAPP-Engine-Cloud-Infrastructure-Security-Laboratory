import java.util.concurrent.PriorityBlockingQueue;
import java.util.Random;

public class SimuladorCPU {
    private final PriorityBlockingQueue<Tarefa> filaEspera = new PriorityBlockingQueue<>();
    private volatile boolean rodando = true;

    public void iniciar() {
        Thread produtor = new Thread(() -> {
            Random random = new Random();
            int contador = 1;
            try {
                while (rodando) {
                    Thread.sleep(random.nextInt(1500) + 500);
                    Tarefa nova = new Tarefa(
                            "Processo_" + contador++,
                            random.nextInt(1000) + 200,
                            random.nextInt(10) + 1
                    );
                    System.out.println("📥 [Produtor] Injetou na Heap: " + nova);
                    filaEspera.put(nova);
                }
            } catch (InterruptedException e) {
                System.out.println("⚠️ Thread Produtora foi interrompida.");
            }
        }, "Thread-Produtora");


        Thread cpu = new Thread(() -> {
            try {
                while (rodando || !filaEspera.isEmpty()) {
                    Tarefa tarefaAtual = filaEspera.take();

                    System.out.println("▶️ [CPU] Executando tarefa crítica: " + tarefaAtual.getNome());

                    Thread.sleep(tarefaAtual.getExecucaoMs());

                    System.out.println("✅ [CPU] Concluída com sucesso: " + tarefaAtual.getNome());
                }
            } catch (InterruptedException e) {
                System.out.println("🔴 [CPU] Erro crítico: A CPU foi interrompida durante a execução de uma tarefa!");
            }
        }, "Thread-CPU-Consumidora");

        produtor.start();
        cpu.start();

        new Thread(() -> {
            try {
                Thread.sleep(15000);
                System.out.println("\n🛑 Encerrando simulação da CPU...");
                rodando = false;
                produtor.interrupt();
                cpu.interrupt();
            } catch (InterruptedException ignored) {}
        }).start();
    }

    public static void main(String[] args) {
        System.out.println("=== INICIANDO SIMULADOR DE ESCALONADOR ASSÍNCRONO ===");
        new SimuladorCPU().iniciar();
    }
}
