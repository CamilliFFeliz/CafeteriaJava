package view;

import controller.ProdutoController;
import java.util.Scanner;

public class ProdutoView implements MenuView {
    
    private ProdutoController controller = new ProdutoController();
    private Scanner scanner;

    public ProdutoView(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\nNosso Cardápio Delicioso");
            System.out.println("========================================");
            System.out.println("1. Cadastrar uma nova Gostosura");
            System.out.println("2. Ver todas as nossas Gostosuras");
            System.out.println("3. Remover uma Gostosura do cardápio");
            System.out.println("0. Voltar ao Salão Principal");
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
                    adicionarProduto();
                    break;
                case 2:
                    controller.listarProdutos();
                    break;
                case 3:
                    removerProduto();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void adicionarProduto() {
        System.out.print("Nome da gostosura: ");
        String nome = scanner.nextLine();
        
        System.out.print("Preço (ex: 15.50): ");
        try {
            double preco = Double.parseDouble(scanner.nextLine());
            if (preco < 0) {
                System.out.println("O preço não pode ser negativo, miau!");
            } else {
                controller.adicionarProduto(nome, preco);
            }
        } catch (NumberFormatException e) {
            System.out.println("Preço inválido. A operação foi cancelada.");
        }
    }

    private void removerProduto() {
        System.out.print("ID da gostosura para remover: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            controller.removerProduto(id);
        } catch (NumberFormatException e) {
            System.out.println("ID inválido! Deve ser um número.");
        }
    }
}
