public class Registro {
    private final String id;
    private final String nome;
    private final int idade;
    private final String cidade;

    public Registro(String id, String nome, int idade, String cidade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.cidade = cityFormat(cidade);
    }

    private String cityFormat(String cidade) {
        return cidade.trim().toUpperCase();
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public String getCidade() { return cidade; }


    public String paraLinhaCSV() {
        return String.format("%s;%s;%d;%s", id, nome, idade, cidade);
    }

    public static Registro deLinhaCSV(String linha) {
        String[] partes = linha.split(";");
        return new Registro(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3]);
    }

    @Override
    public String toString() {
        return String.format("[ID: %s | Nome: %s | Idade: %d | Cidade: %s]", id, nome, idade, cidade);
    }
}
