import java.util.List;

public class TesteBanco {
    public static void main(String[] args) {

        MiniBancoDados db = new MiniBancoDados("tabela_usuarios.txt");

        System.out.println("=== POPULANDO O BANCO DE DADOS ===");
        db.inserir(new Registro("101", "Ana Silva", 28, "Varzea Paulista"));
        db.inserir(new Registro("102", "Bernardo Costa", 34, "Jundiai"));
        db.inserir(new Registro("103", "Carlos Souza", 22, "Varzea Paulista"));
        db.inserir(new Registro("104", "Daniela Oliveira", 41, "Campinas"));

        System.out.println("\n=== TESTANDO O PIPELINE DE BUSCA ===");

        Registro reg1 = db.buscarPorId("101");
        System.out.println("Resultado: " + reg1);


        System.out.println("\n Simulando reinicialização do cache (Limpando RAM)...");
        MiniBancoDados dbReiniciado = new MiniBancoDados("tabela_usuarios.txt");

        Registro reg2 = dbReiniciado.buscarPorId("103");
        System.out.println("Resultado: " + reg2);


        Registro reg2Novamente = dbReiniciado.buscarPorId("103");
        System.out.println("Resultado: " + reg2Novamente);

        List<Registro> queryResult = dbReiniciado.executarQueryCidade("Varzea Paulista");
        System.out.println("Registros encontrados:");
        queryResult.forEach(System.out::println);

    }
}
