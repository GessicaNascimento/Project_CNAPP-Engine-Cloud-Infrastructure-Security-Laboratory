public class TesteMap {
    public static void main(String[] args) {
        MyHashMap<String, String> mapaDeVagas = new MyHashMap<>();

        mapaDeVagas.put("A1", "Corsa");
        mapaDeVagas.put("B2", "Civic");

        mapaDeVagas.put("Gessica", "Desenvolvedora");
        mapaDeVagas.put("Ruca", "Matemática");

        System.out.println("=== LEITURA DO MAPA ===");
        System.out.println("O ocupante de A1 é: " + mapaDeVagas.get("A1"));
        System.out.println("A profissão de Gessica é: " + mapaDeVagas.get("Gessica"));
        System.out.println("A área de Ruca é: " + mapaDeVagas.get("Ruca"));

        mapaDeVagas.put("A1", "Ferrari");
        System.out.println("O novo ocupante de A1 após atualização é: " + mapaDeVagas.get("A1"));
    }
}