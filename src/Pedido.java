import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Pedido {
    private static int contador = 1;
    private String rua;
    private String CEP;
    private int numero;
    private LocalTime hora;
    private int qtdBotijoes;
    private float precoBotijao;
    private int formaPag;
    private int nPedido;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFormaPag() {
        return formaPag;
    }

    public void setFormaPag(int formaPag) {
        this.formaPag = formaPag;
    }

    public Pedido() {
        this.nPedido = contador++;
        this.rua = rua;
        this.CEP = CEP;
        this.numero = numero;
        this.hora = LocalTime.now();
        this.qtdBotijoes = qtdBotijoes;
        this.precoBotijao = 100f;
        this.formaPag = formaPag;
        this.status = status;
    }

    public int getnPedido() {
        return nPedido;
    }

    public float getPrecoBotijao() {
        return precoBotijao;
    }

    public void setPrecoBotijao(float precoBotijao) {
        this.precoBotijao = precoBotijao;
    }

    public int getQtdBotijoes() {
        return qtdBotijoes;
    }

    public void setQtdBotijoes(int qtdBotijoes) {
        this.qtdBotijoes = qtdBotijoes;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public float calcula() {
        return qtdBotijoes * precoBotijao;
    }

    public LocalTime entrega() {
        return LocalTime.now().plusHours(6);
    }

    public void setEndereco() {

        Scanner sc = new Scanner(System.in);
        System.out.println("ENDEREÇO");
        System.out.println("Rua:");
        setRua(sc.nextLine());

        System.out.println("CEP:");
        setCEP(sc.nextLine());

        System.out.println("Número:");
        setNumero(sc.nextInt());
        sc.nextLine();

    }

    public static Pedido fazerPedido() {
        Pedido pedido = new Pedido();
        int escolha;
        boolean confirmado = false;
        Scanner sc = new Scanner(System.in);

        while (!confirmado) {

            pedido.setEndereco();

            LocalTime localTime = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            pedido.setHora(localTime);


            System.out.println("Endereço:" + pedido.getRua() + ", " + pedido.getCEP() + ", " + pedido.getNumero());
            System.out.println("Pedido feito às " + localTime.format(formatter));
            System.out.println("Para confirmar digite 1, para alterar digite 0:");

            escolha = sc.nextInt();
            sc.nextLine();

            if (escolha == 0) {
                pedido.setEndereco();
            } else {
                confirmado = true;
            }
        }

        System.out.println("Preço do botijão R$ " + pedido.getPrecoBotijao());
        System.out.println("Quantos botijões deseja comprar?");
        pedido.setQtdBotijoes(sc.nextInt());

        System.out.println("Total da compra: " + "R$ " + pedido.calcula());

        LocalTime horaEntrega = pedido.entrega();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm");

        if (horaEntrega.isAfter(LocalTime.now())) {
            System.out.println("Horário de entrega: " + horaEntrega.format(formatter1));
        } else {
            System.out.println("Horário de entrega: " + horaEntrega.format(formatter1) + "de amanhã!");
        }

        System.out.println("Digite o número do cartão de crédito:");
        pedido.setFormaPag(sc.nextInt());
        sc.nextLine();
        pedido.setStatus("Confirmado");
        System.out.println("Código do pedido: " + pedido.getnPedido());

        return pedido;
    }

    public static void confirmarEntrega(List<Pedido> pedidos){
        Scanner sc = new Scanner(System.in);
        int codigo;
        System.out.println("Digite o código do pedido: ");
        codigo = sc.nextInt();
        sc.nextLine();
        boolean encontrado = false;
        for (Pedido p : pedidos) {
            if (p.getnPedido() == codigo) {
                p.setStatus("Entregue");
                encontrado = true;
                System.out.println("Status do pedido atualizado : " + p.getStatus());
                break;
            }
            if (!encontrado) System.out.println("Pedido não localizado");
        }
    }

    public static void pedidosConfirmados(List<Pedido> pedidos) {
        for (Pedido pedido : pedidos) {
            if (pedido.getStatus().equals("Confirmado")) {
                System.out.println(pedido);
            }
        }
    }

    public static void pedidosEntregues(List<Pedido> pedidos) {
        for (Pedido P : pedidos) {
            if (P.getStatus().equals("Entregue")) {
                System.out.println(P);
            }
        }
    }

    @Override
    public String toString() {
        return "Pedido: " + nPedido + ", " +
                "Status: " + status + ", "+
                "Endereço: " + rua + ", " + CEP + ", " + numero + ", "+
                "Hora: " + hora + ", "+
                "Valor total: " + calcula();
    }
}
