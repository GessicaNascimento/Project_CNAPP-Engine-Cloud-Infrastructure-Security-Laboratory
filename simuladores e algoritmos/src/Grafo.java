import java.util.*;

public class Grafo {
    private final Map<String, List<Aresta>> adjacencia = new HashMap<>();

    public void adicionarVertice(String nome) {
        adjacencia.putIfAbsent(nome, new ArrayList<>());
    }

    public void adicionarAresta(String origem, String destino, int peso) {
        adicionarVertice(origem);
        adicionarVertice(destino);

        adjacencia.get(origem).add(new Aresta(destino, peso));
    }

    public List<Aresta> getAdjacentes(String vertice) {
        return adjacencia.getOrDefault(vertice, Collections.emptyList());
    }

    public Set<String> getVertices() {
        return adjacencia.keySet();
    }
}


