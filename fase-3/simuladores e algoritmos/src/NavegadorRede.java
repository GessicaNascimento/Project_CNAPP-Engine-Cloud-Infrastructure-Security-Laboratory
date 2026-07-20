import java.util.*;

public class NavegadorRede {

    private static class NoDistancia implements Comparable<NoDistancia> {
        String vertice;
        int distanciaAcumulada;

        NoDistancia(String vertice, int distanciaAcumulada) {
            this.vertice = vertice;
            this.distanciaAcumulada = distanciaAcumulada;
        }

        @Override
        public int compareTo(NoDistancia outro) {
            return Integer.compare(this.distanciaAcumulada, outro.distanciaAcumulada);
        }
    }

    public static List<String> calcularCaminhoMinimo(Grafo grafo, String origem, String destino) {
        Map<String, Integer> distancias = new HashMap<>();
        Map<String, String> predecessores = new HashMap<>();
        PriorityQueue<NoDistancia> filaPrioridade = new PriorityQueue<>();
        Set<String> visitados = new HashSet<>();

        for (String v : grafo.getVertices()) {
            distancias.put(v, Integer.MAX_VALUE);
        }
        distancias.put(origem, 0);
        filaPrioridade.add(new NoDistancia(origem, 0));

        while (!filaPrioridade.isEmpty()) {
            NoDistancia atual = filaPrioridade.poll();
            String u = atual.vertice;

            if (visitados.contains(u)) continue;
            visitados.add(u);

            if (u.equals(destino)) break;


            for (Aresta aresta : grafo.getAdjacentes(u)) {
                String v = aresta.getDestino();
                int peso = aresta.getPeso();

                if (!visitados.contains(v)) {
                    int novaDistancia = distancias.get(u) + peso;

                    if (novaDistancia < distancias.get(v)) {
                        distancias.put(v, novaDistancia);
                        predecessores.put(v, u);
                        filaPrioridade.add(new NoDistancia(v, novaDistancia));
                    }
                }
            }
        }

        List<String> caminho = new ArrayList<>();
        String passo = destino;
        if (distancias.get(destino) == Integer.MAX_VALUE) {
            return Collections.emptyList();
        }

        while (passo != null) {
            caminho.add(0, passo);
            passo = predecessores.get(passo);
        }

        System.out.println("⚡ Custo total do trajeto (métrica/latência): " + distancias.get(destino));
        return caminho;
    }

    public static void main(String[] args) {
        Grafo rede = new Grafo();

        rede.adicionarAresta("Roteador_A", "Roteador_B", 4);
        rede.adicionarAresta("Roteador_A", "Roteador_C", 2);
        rede.adicionarAresta("Roteador_B", "Roteador_C", 1);
        rede.adicionarAresta("Roteador_B", "Roteador_D", 5);
        rede.adicionarAresta("Roteador_C", "Roteador_D", 8);
        rede.adicionarAresta("Roteador_C", "Roteador_E", 10);
        rede.adicionarAresta("Roteador_D", "Roteador_E", 2);

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== ROTEAR REDE (Algoritmo de Dijkstra) ===");
        System.out.print("Digite o roteador de ORIGEM (ex: Roteador_A): ");
        String origem = scanner.nextLine();
        System.out.print("Digite o roteador de DESTINO (ex: Roteador_E): ");
        String destino = scanner.nextLine();

        List<String> rota = calcularCaminhoMinimo(rede, origem, destino);

        if (rota.isEmpty()) {
            System.out.println("❌ Não foi possível encontrar uma rota válida entre os pontos informados.");
        } else {
            System.out.println("🧭 Rota otimizada calculada:");
            System.out.println(String.join(" ──► ", rota));
        }
        scanner.close();
    }
}
