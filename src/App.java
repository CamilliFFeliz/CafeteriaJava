// Integrantes: Alana Cristina Martens - 38225221 | Camilli Frigeri Feliz - 37280571 | Mariela Barbosa da Silva - 37953095 | Vitor Manoel Rodrigues Carvalho - 38724626 | Samantha de Souza Andrade - 37284886

import controller.ClienteController;
import controller.PedidoController;
import controller.ProdutoController;
import Dal.ClienteDAO;
import Dal.PedidoDAO;
import Dal.ProdutoDAO;
import view.ClienteView;
import view.MenuView;
import view.PedidoView;
import view.ProdutoView;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        ClienteDAO clienteDAO = new ClienteDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        PedidoDAO pedidoDAO = new PedidoDAO(clienteDAO, produtoDAO);

        ClienteController clienteController = new ClienteController(clienteDAO);
        ProdutoController produtoController = new ProdutoController(produtoDAO);
        PedidoController pedidoController = new PedidoController(pedidoDAO);

        inicializarDados(clienteController, produtoController);

        try (Scanner scanner = new Scanner(System.in)) {
            exibirMenuPrincipal(scanner, clienteController, produtoController, pedidoController);
        }

        System.out.println("\nAté a próxima! Os gatinhos sentirão sua falta! Miau!");
    }


    private static void inicializarDados(ClienteController clienteController, ProdutoController produtoController) {
        if (clienteController.listarTodos().isEmpty()) {
            System.out.println("Sistema: Criando cliente padrão 'Amigo do Café'...");
            clienteController.adicionarCliente("Amigo do Café", "Balcão", "N/A");
        }
        if (produtoController.listarTodos().isEmpty()) {
            System.out.println("Sistema: Criando nosso cardápio inicial...");
            produtoController.adicionarProduto("Café Ex-presso Gato", 5.00);
            produtoController.adicionarProduto("Capurrccino", 8.50);
        }
    }
    
    private static void exibirMenuPrincipal(Scanner scanner, ClienteController cCtrl, ProdutoController pCtrl, PedidoController peCtrl) {
        int opcao = -1;
        do {
            System.out.println("\n--- Bem-vindo ao Café Gatinhos Fofos ---");
            System.out.println("1. Gerenciar Clientes");
            System.out.println("2. Gerenciar Cardápio (Produtos)");
            System.out.println("3. Gerenciar Pedidos");
            System.out.println("0. Sair");
            System.out.print("Opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        new ClienteView(scanner, cCtrl).exibirMenu();
                        break;
                    case 2:
                        new ProdutoView(scanner, pCtrl).exibirMenu();
                        break;
                    case 3:
                        new PedidoView(scanner, peCtrl, cCtrl, pCtrl).exibirMenu();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("[ERRO] Opção inválida, miau!");
                }
            } catch (NumberFormatException e) {
                System.out.println("[ERRO] Entrada inválida. Por favor, digite um número.");
                opcao = -1;
            } catch (Exception e) {

                System.out.println("[ERRO GERAL] Ocorreu um problema: " + e.getMessage());
            }
        } while (opcao != 0);
    }
}