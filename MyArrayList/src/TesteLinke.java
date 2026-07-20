public class TesteLinke {
    public static void main(String[] args) {

        MyLinkedList<String> lista = new MyLinkedList<>();
        lista.add("Apple");
        lista.add("Android");
        lista.add("Xaiomi");
        lista.add("Samsung");

        System.out.println(" O aparelho "  + lista.get(0) +  " é mais caro! ");
        System.out.println(" O elemento do último índice: "   +  lista.get(3) +  " está disponível! ");
        System.out.println("O tamanho atual da lista é: " + lista.size());



    }
}
