import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<Pedido> pedidos = new ArrayList<Pedido>();
        Scanner sc = new Scanner(System.in);
        int escolha;

        do {
            System.out.println(" Menu");
            System.out.println("1- Fazer Pedido");
            System.out.println("2- Ver pedidos confirmados");
            System.out.println("3- Confirmar entrega");
            System.out.println("4- Ver pedidos entregues");
            System.out.println("5- Sair");

            escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1 -> {
                   Pedido pedido = Pedido.fazerPedido();
                    pedidos.add(pedido);
                }

                case 2 -> {
                    Pedido.pedidosConfirmados(pedidos);
                }
                case 3 -> {
                    Pedido.confirmarEntrega(pedidos);
                }
                case 4 -> {
                   Pedido.pedidosEntregues(pedidos);
                }

                case 5 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida. Tente Novamente.");

            }
        } while (escolha != 5);
        sc.close();
    }
}

