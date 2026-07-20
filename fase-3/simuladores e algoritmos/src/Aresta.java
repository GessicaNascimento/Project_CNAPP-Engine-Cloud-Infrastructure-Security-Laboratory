public class Aresta {
    private final String destino;
    private final int peso;

    public Aresta(String destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }

    public String getDestino() { return destino; }
    public int getPeso() { return peso; }
}
