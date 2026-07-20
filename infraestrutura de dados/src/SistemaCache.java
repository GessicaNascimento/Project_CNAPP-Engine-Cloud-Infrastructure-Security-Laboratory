import java.util.HashSet;
import java.util.TreeMap;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class SistemaCache {
    private final Set<String> chavesExistentes = new HashSet<>();
    private final Map<String, Registro> memoriaRAM = new TreeMap<>();


    private final Map<String, List<String>> indiceCoordenadasCidade = new TreeMap<>();

    public void adicionar(Registro registro) {
        memoriaRAM.put(registro.getId(), registro);
        chavesExistentes.add(registro.getId());

        String cidadePonta = registro.getCidade();
        indiceCoordenadasCidade.putIfAbsent(cidadePonta, new ArrayList<>());

        if (!indiceCoordenadasCidade.get(cidadePonta).contains(registro.getId())) {
            indiceCoordenadasCidade.get(cidadePonta).add(registro.getId());
        }
    }

    public Registro obter(String id) {
        return memoriaRAM.get(id);
    }

    public boolean contem(String id) {
        return chavesExistentes.contains(id);
    }

    public List<String> obterCoordenadasPorCidade(String cidade) {
        return indiceCoordenadasCidade.getOrDefault(cidade, Collections.emptyList());
    }

    public void limpar() {
        memoriaRAM.clear();
        chavesExistentes.clear();
        indiceCoordenadasCidade.clear();
    }
}


