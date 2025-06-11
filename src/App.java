import view.ClienteView;
import view.MenuView;
import view.PedidoView;
import view.ProdutoView;
import java.util.Scanner;
import controller.ClienteController;
import controller.ProdutoController;

public class App {

    public static void main(String[] args) {
        inicializarDados();
        try (Scanner scanner = new Scanner(System.in)) {
            exibirMenuPrincipal(scanner);
        }
        System.out.println("\nAté a próxima! Os gatinhos sentirão sua falta! Miau!");
    }

    private static void inicializarDados() {
        ClienteController clienteController = new ClienteController();
        ProdutoController produtoController = new ProdutoController();
        if (clienteController.listarTodos().isEmpty()) {
            System.out.println("Sistema: Nenhum cliente encontrado. Criando cliente padrão 'Amigo do Café'...");
            clienteController.adicionarCliente("Amigo do Café", "Balcão", "N/A");
        }
        if (produtoController.listarTodos().isEmpty()) {
            System.out.println("Sistema: Nenhum produto encontrado. Criando o nosso cardápio...");
            produtoController.adicionarProduto("Café Ex-presso Gato", 5.00);
            produtoController.adicionarProduto("Capurrccino", 8.50);
            produtoController.adicionarProduto("Chocolate Quente Felino", 7.00);
            produtoController.adicionarProduto("Pão de Queijo Gatinho", 4.50);
        }
    }

    private static void exibirMenuPrincipal(Scanner scanner) {
        MenuView menuAtual;
        int opcao = -1;
        do {
            System.out.println("\nBem-vindo ao Café Gatinhos Fofos");
            System.out.println("O que você e os gatinhos desejam fazer?\n");
            System.out.println("1. Você é novo aqui? Cadastre-se");
            System.out.println("2. Confira nosso Cardápio Delicioso");
            System.out.println("3. Realize seu Pedido Ronronante");
            System.out.println("0. Sair e receber um até logo dos gatinhos");
            System.out.print("Opção: ");

            try {
                String entrada = scanner.nextLine();
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                opcao = -1;
                continue;
            }
            switch (opcao) {
                case 1:
                    menuAtual = new ClienteView(scanner);
                    menuAtual.exibirMenu();
                    break;
                case 2:
                    menuAtual = new ProdutoView(scanner);
                    menuAtual.exibirMenu();
                    break;
                case 3:
                    menuAtual = new PedidoView(scanner);
                    menuAtual.exibirMenu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida, miau!");
            }
        } while (opcao != 0);
    }
}
