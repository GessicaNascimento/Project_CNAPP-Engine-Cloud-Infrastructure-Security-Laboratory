import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MiniBancoDados {
    private final Path caminhoArquivo;
    private final SistemaCache cache;

    public MiniBancoDados(String nomeArquivo) {
        this.caminhoArquivo = Paths.get(nomeArquivo);
        this.cache = new SistemaCache();
        inicializarArquivo();
        carregarEIndiciarBanco();
    }

    private void inicializarArquivo() {
        try {
            if (!Files.exists(caminhoArquivo)) {
                Files.createFile(caminhoArquivo);
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao inicializar o arquivo", e);
        }
    }

    private void carregarEIndiciarBanco() {
        try (Stream<String> linhas = Files.lines(caminhoArquivo)) {
            linhas.filter(l -> !l.trim().isEmpty())
                    .map(Registro::deLinhaCSV)
                    .forEach(cache::adicionar);
        } catch (IOException e) {
            System.err.println("Erro ao carregar banco para indexação: " + e.getMessage());
        }
    }

    public void inserir(Registro registro) {
        try {
            String linha = registro.paraLinhaCSV() + System.lineSeparator();
            Files.write(caminhoArquivo, linha.getBytes(), StandardOpenOption.APPEND);
            cache.adicionar(registro);
            System.out.println("[Engine] Gravado e coordenado no cache: " + registro.getId());
        } catch (IOException e) {
            System.err.println("Erro ao gravar dados: " + e.getMessage());
        }
    }

    public List<Registro> executarQueryCidade(String cidadeBusca) {
        String cidadeAlvo = cidadeBusca.trim().toUpperCase();
        System.out.println("\n[SQL Engine - Busca de Pontas] SELECT * WHERE CIDADE = '" + cidadeAlvo + "'");

        List<String> idsMapeados = cache.obterCoordenadasPorCidade(cidadeAlvo);

        if (idsMapeados.isEmpty()) {
            System.out.println("Nenhuma coordenada encontrada para a ponta especificada.");
            return new ArrayList<>();
        }

        System.out.println("[Sucesso] Coordenadas encontradas via Índice Invertido: " + idsMapeados);


        return idsMapeados.stream()
                .map(this::buscarPorId)
                .collect(Collectors.toList());
    }

    public Registro buscarPorId(String id) {
        if (cache.contem(id)) {
            return cache.obter(id);
        }

        try (Stream<String> linhas = Files.lines(caminhoArquivo)) {
            return linhas
                    .map(Registro::deLinhaCSV)
                    .filter(reg -> reg.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            return null;
        }
    }
}
