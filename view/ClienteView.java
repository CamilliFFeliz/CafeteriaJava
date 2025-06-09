package view;

import controller.ClienteController;
import model.Cliente;
import java.util.Scanner;

public class ClienteView {
    private ClienteController controller = new ClienteController();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        int opcao;
        do {
            System.out.println("\n-- Menu Cliente --");
            System.out.println("1. Adicionar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Remover Cliente");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String endereco = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();
                    Cliente c = new Cliente(controller.getClientes().size() + 1, nome, endereco, telefone);
                    controller.adicionarCliente(c);
                    break;
                case 2:
                    controller.listarClientes();
                    break;
                case 3:
                    System.out.print("ID do cliente para remover: ");
                    int id = scanner.nextInt();
                    controller.removerCliente(id);
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}