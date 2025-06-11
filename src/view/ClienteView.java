package view;

import controller.ClienteController;
import java.util.Scanner;

// 1. Adicione "implements MenuView"
public class ClienteView implements MenuView {
    
    private ClienteController controller = new ClienteController();
    private Scanner scanner = new Scanner(System.in);

    // 2. Renomeie o método para "exibirMenu" e adicione @Override
    @Override
    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n-- Menu Cliente --");
            System.out.println("1. Adicionar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Remover Cliente");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                opcao = -1; // Define um valor inválido para continuar no loop
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String endereco = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();
                    controller.adicionarCliente(nome, endereco, telefone);
                    break;
                case 2:
                    controller.listarClientes();
                    break;
                case 3:
                    System.out.print("ID do cliente para remover: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine());
                        controller.removerCliente(id);
                    } catch (NumberFormatException e) {
                        System.out.println("ID inválido! Deve ser um número.");
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}