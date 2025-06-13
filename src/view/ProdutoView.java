package view;

import controller.ProdutoController;
import model.Produto;
import java.util.List;
import java.util.Scanner;

public class ProdutoView implements MenuView {

    private final Scanner scanner;
    private final ProdutoController controller;

    public ProdutoView(Scanner scanner, ProdutoController controller) {
        this.scanner = scanner;
        this.controller = controller;
    }

    @Override
    public void exibirMenu() {
        int opcao = -1;
        do {
            System.out.println("\n--- Cardápio Delicioso ---");
            System.out.println("================================");
            System.out.println("1. Adicionar nova gostosura");
            System.out.println("2. Ver todas as gostosuras");
            System.out.println("3. Remover uma gostosura");
            System.out.println("0. Voltar para o Salão Principal");
            System.out.print("Opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        adicionarProduto();
                        break;
                    case 2:
                        listarProdutos();
                        break;
                    case 3:
                        removerProduto();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("[ERRO] Opção inválida, miau!");
                }
            } catch (NumberFormatException e) {
                System.out.println("[ERRO] Entrada inválida. Por favor, digite um número.");
            } catch (Exception e) {
                System.out.println("[ERRO INESPERADO] " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private void adicionarProduto() {
        System.out.println("\n--- Adicionar Nova Gostosura ---");
        System.out.print("Nome da gostosura: ");
        String nome = scanner.nextLine();
        
        System.out.print("Preço (ex: 5.50): ");
        double preco = Double.parseDouble(scanner.nextLine());

        controller.adicionarProduto(nome, preco);
        System.out.println("'" + nome + "' adicionado ao cardápio com sucesso!");
    }

    private void listarProdutos() {
        System.out.println("\n--- Todas as Gostosuras do Cardápio ---");
        
        List<Produto> produtos = controller.listarTodos();

        if (produtos.isEmpty()) {
            System.out.println("Nenhuma gostosura cadastrada ainda.");
        } else {
            for (Produto produto : produtos) {
                System.out.printf("ID: %d | Nome: %s | Preço: R$ %.2f\n",
                        produto.getId(), produto.getNome(), produto.getPreco());
            }
        }
    }

    private void removerProduto() {
        System.out.print("\nDigite o ID da gostosura para remover: ");
        int id = Integer.parseInt(scanner.nextLine());
        controller.removerProduto(id);
        System.out.println("Remoção processada. Verifique a lista para confirmar.");
    }
}