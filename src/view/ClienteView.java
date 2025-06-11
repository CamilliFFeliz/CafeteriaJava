package view;

import controller.ClienteController;
import java.util.Scanner;

public class ClienteView implements MenuView {
    
    private ClienteController controller = new ClienteController();
    private Scanner scanner;

    public ClienteView(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void exibirMenu() {
        int opcao = -1;
        do {
            System.out.println("\nCantinho dos Amigos Humanos");
            System.out.println("========================================");
            System.out.println("1. Cadastrar um novo Amigo Humano");
            System.out.println("2. Ver todos os nossos Amigos");
            System.out.println("3. Remover um Amigo da lista");
            System.out.println("0. Voltar para o Salão Principal");
            System.out.print("Opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                opcao = -1;
                continue;
            }
            switch (opcao) {
                case 1:
                    adicionarCliente();
                    break;
                case 2:
                    controller.listarClientes();
                    break;
                case 3:
                    removerCliente();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void adicionarCliente() {
        System.out.print("Nome do nosso novo amigo: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Telefone para contato: ");
        String telefone = scanner.nextLine();
        controller.adicionarCliente(nome, endereco, telefone);
    }

    private void removerCliente() {
        System.out.print("ID do amigo humano para remover: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            controller.removerCliente(id);
        } catch (NumberFormatException e) {
            System.out.println("ID inválido! Deve ser um número, miau.");
        }
    }
}