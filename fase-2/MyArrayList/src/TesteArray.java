public class TesteArray {
    public static void main(String[] args) {

        MyArrayList <String> array = new MyArrayList<>();
        array.add("Java");
        array.add("Rust");
        array.add("C");

        System.out.println("Elemento no índice 0: " + array.get(0)); // Saída: Java
        System.out.println("Elemento no índice 1: " + array.get(1)); // Saída: Rust
        System.out.println("Elemento no índice 2: " + array.get(2)); // Saída: C

        System.out.println("O tamanho atual do array é: " + array.size()); // Saída: 3



    }


}
