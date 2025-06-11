package view;

import controller.ClienteController;
import controller.PedidoController;
import controller.ProdutoController;
import model.Cliente;
import model.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PedidoView implements MenuView {

    private Scanner scanner;
    private PedidoController pedidoController = new PedidoController();
    private ClienteController clienteController = new ClienteController();
    private ProdutoController produtoController = new ProdutoController();

    public PedidoView(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void exibirMenu() {
        int opcao = -1;
        do {
            System.out.println("\nMenu de Pedidos Ronronantes");
            System.out.println("==================================");
            System.out.println("1. Fazer um novo Pedido");
            System.out.println("2. Ver todos os Pedidos fofinhos");
            System.out.println("0. Voltar para o Salão Principal");
            System.out.print("Opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Oops! Digite um número, por favor.");
                continue;
            }

            switch (opcao) {
                case 1:
                    criarNovoPedido();
                    break;
                case 2:
                    pedidoController.listarPedidos();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida, miau!");
            }
        } while (opcao != 0);
    }

    private void criarNovoPedido() {
        System.out.println("\n--- Preparando um novo Pedido... Miau! ---");

        System.out.println("\nPara qual humano é este pedido?");
        clienteController.listarClientes();
        System.out.print("Digite o ID do(a) humano(a): ");
        Cliente clienteSelecionado = null;
        try {
            int clienteId = Integer.parseInt(scanner.nextLine());
            clienteSelecionado = clienteController.buscarClientePorId(clienteId);
            if (clienteSelecionado == null) {
                System.out.println("Oops! Não encontramos esse humano na nossa lista de amigos. Operação cancelada.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Operação cancelada com um miado triste.");
            return;
        }

        List<Produto> produtosDoPedido = new ArrayList<>();
        int produtoId = -1;
        double valorAtual = 0.0;

        do {
            System.out.println("\nEscolha uma gostosura para adicionar ao carrinho:");
            produtoController.listarProdutos();
            System.out.println("-----------------------------------------");
            System.out.println("Para ir para o pagamento, digite 0");
            System.out.println("Sua continha está em: R$ " + String.format("%.2f", valorAtual));
            System.out.print("Digite o ID da gostosura: ");

            try {
                produtoId = Integer.parseInt(scanner.nextLine());
                if (produtoId != 0) {
                    Produto produtoSelecionado = produtoController.buscarProdutoPorId(produtoId);
                    if (produtoSelecionado != null) {
                        produtosDoPedido.add(produtoSelecionado);
                        valorAtual += produtoSelecionado.getPreco();
                        System.out.println("Oba! '" + produtoSelecionado.getNome() + "' foi adicionado com um ronronar!");
                    } else {
                        System.out.println("Não encontramos nenhuma gostosura com o ID " + produtoId + ".");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("ID de produto inválido. Tente novamente, por favor.");
            }
        } while (produtoId != 0);

        if (produtosDoPedido.isEmpty()) {
            System.out.println("O carrinho está vazio... Pedido cancelado com um miado triste.");
        } else {
            pedidoController.criarPedido(clienteSelecionado, produtosDoPedido);
            System.out.println("\n========================================");
            System.out.println("      PEDIDO FEITO COM SUCESSO! Miau!      ");
            System.out.println("========================================");
            System.out.println("Cliente: " + clienteSelecionado.getNome());
            System.out.println("Valor Total: R$ " + String.format("%.2f", valorAtual));
            System.out.println("========================================");
        }
    }
}
