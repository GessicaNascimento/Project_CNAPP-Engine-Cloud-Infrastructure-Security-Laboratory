public class Tarefa implements Comparable<Tarefa> {
    private final String nome;
    private final int execucaoMs;
    private final int prioridade;

    public Tarefa(String nome, int execucaoMs, int prioridade) {
        this.nome = nome;
        this.execucaoMs = execucaoMs;
        this.prioridade = Math.max(1, Math.min(10, prioridade));
    }

    public String getNome() { return nome; }
    public int getExecucaoMs() { return execucaoMs; }
    public int getPrioridade() { return prioridade; }


    @Override
    public int compareTo(Tarefa outra) {
        return Integer.compare(outra.getPrioridade(), this.prioridade);
    }

    @Override
    public String toString() {
        return String.format("[%s | Prioridade: %d | Tempo: %dms]", nome, prioridade, execucaoMs);
    }
}
