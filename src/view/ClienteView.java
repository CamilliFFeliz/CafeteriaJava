package view;

import controller.ClienteController;
import model.Cliente;

import java.util.List;
import java.util.Scanner;

public class ClienteView implements MenuView {

    private ClienteController controller;
    private Scanner scanner;

    public ClienteView(Scanner scanner, ClienteController controller) {
        this.scanner = scanner;
        this.controller = controller; 
    }

    @Override
    public void exibirMenu() {
        int opcao = -1;
        do {
            System.out.println("\n--- Cantinho dos Amigos Humanos ---");
            System.out.println("===================================");
            System.out.println("1. Cadastrar um novo Amigo Humano");
            System.out.println("2. Ver todos os nossos Amigos");
            System.out.println("3. Remover um Amigo da lista");
            System.out.println("0. Voltar para o Salão Principal");
            System.out.print("Opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\n[ERRO] Entrada inválida. Por favor, digite um número.");
                opcao = -1;
                continue;
            }
            
            try {
                switch (opcao) {
                    case 1:
                        adicionarCliente();
                        break;
                    case 2:
                        listarClientes();
                        break;
                    case 3:
                        removerCliente();
                        break;
                    case 0:
                        System.out.println("Voltando ao Salão Principal...");
                        break;
                    default:
                        System.out.println("\n[ERRO] Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("\n[ERRO INESPERADO] " + e.getMessage());
            }

        } while (opcao != 0);
    }

    private void adicionarCliente() {
        System.out.println("\n--- Cadastro de Novo Amigo ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        try {
            controller.adicionarCliente(nome, endereco, telefone);
            System.out.println("\nAmigo Humano adicionado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("\n[ERRO NO CADASTRO] " + e.getMessage());
        }
    }

    private void listarClientes() {
        List<Cliente> clientes = controller.listarTodos();

        System.out.println("\n--- Lista de Amigos Humanos ---");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum amigo humano cadastrado ainda.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println("---------------------------------");
                System.out.printf("ID: %d | Nome: %s | Endereço: %s | Tel: %s\n",
                        cliente.getId(), cliente.getNome(), cliente.getEndereco(), cliente.getTelefone());
            }
            System.out.println("---------------------------------");
        }
    }

    private void removerCliente() {
        System.out.println("\n--- Remover Amigo Humano ---");
        System.out.print("Digite o ID do amigo para remover: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            controller.removerCliente(id);
            System.out.println("\nRemoção processada. Verifique a lista para confirmar.");
        } catch (NumberFormatException e) {
            System.out.println("\n[ERRO] ID inválido! Deve ser um número.");
        }
    }
}