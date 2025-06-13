package view;

import controller.ClienteController;
import controller.PedidoController;
import controller.ProdutoController;
import model.Cliente;
import model.Pedido;
import model.Produto;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PedidoView implements MenuView {

    private final Scanner scanner;
    private final PedidoController pedidoController;
    private final ClienteController clienteController;
    private final ProdutoController produtoController;

    public PedidoView(Scanner scanner, PedidoController pedidoController, ClienteController clienteController, ProdutoController produtoController) {
        this.scanner = scanner;
        this.pedidoController = pedidoController;
        this.clienteController = clienteController;
        this.produtoController = produtoController;
    }

    @Override
    public void exibirMenu() {
        int opcao = -1;
        do {
            System.out.println("\n--- Menu de Pedidos Ronronantes ---");
            System.out.println("=====================================");
            System.out.println("1. Fazer um novo Pedido");
            System.out.println("2. Ver todos os Pedidos");
            System.out.println("0. Voltar para o Salão Principal");
            System.out.print("Opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        criarNovoPedido();
                        break;
                    case 2:
                        listarTodosPedidos();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("[ERRO] Opção inválida, miau!");
                }
            } catch (NumberFormatException e) {
                System.out.println("[ERRO] Oops! Digite um número, por favor.");
            } catch (Exception e) {
                System.out.println("[ERRO INESPERADO] " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private void listarTodosPedidos() {
        System.out.println("\n--- Todos os Pedidos Fofinhos ---");
        List<Pedido> pedidos = pedidoController.getPedidos();
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado.");
            return;
        }
        for (Pedido pedido : pedidos) {
            System.out.println("---------------------------------");
            System.out.println(pedido);
        }
        System.out.println("---------------------------------");
    }

    private void criarNovoPedido() {
        System.out.println("\n--- Preparando um novo Pedido... Miau! ---");

        List<Cliente> clientes = clienteController.listarTodos();
        if(clientes.isEmpty()){
            System.out.println("[ERRO] Não há clientes cadastrados. Cadastre um cliente primeiro.");
            return;
        }
        System.out.println("\nPara qual humano é este pedido?");
        clientes.forEach(c -> System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome()));
        System.out.print("Digite o ID do(a) humano(a): ");
        
        Cliente clienteSelecionado;
        try {
            int clienteId = Integer.parseInt(scanner.nextLine());
            clienteSelecionado = clienteController.buscarClientePorId(clienteId);
            if (clienteSelecionado == null) {
                System.out.println("[ERRO] Não encontramos esse humano. Operação cancelada.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("[ERRO] ID inválido. Operação cancelada com um miado triste.");
            return;
        }

        List<Produto> produtosDoPedido = new ArrayList<>();
        int produtoId;
        double valorAtual = 0.0;
        do {
            System.out.println("\nEscolha uma gostosura para adicionar ao carrinho (digite 0 para finalizar):");
            produtoController.listarTodos().forEach(p -> System.out.printf("ID: %d | %s | R$ %.2f\n", p.getId(), p.getNome(), p.getPreco()));
            System.out.print("Digite o ID da gostosura: ");
            
            produtoId = Integer.parseInt(scanner.nextLine());
            if (produtoId != 0) {
                Produto produtoSelecionado = produtoController.buscarProdutoPorId(produtoId);
                if (produtoSelecionado != null) {
                    produtosDoPedido.add(produtoSelecionado);
                    valorAtual += produtoSelecionado.getPreco();
                    System.out.println("Oba! '" + produtoSelecionado.getNome() + "' foi adicionado com um ronronar!");
                } else {
                    System.out.println("[ERRO] Não encontramos nenhuma gostosura com o ID " + produtoId + ".");
                }
            }
        } while (produtoId != 0);

        if (produtosDoPedido.isEmpty()) {
            System.out.println("O carrinho está vazio... Pedido cancelado com um miado triste.");
        } else {
            pedidoController.criarPedido(clienteSelecionado, produtosDoPedido);
            System.out.println("\n========================================");
            System.out.println("   PEDIDO FEITO COM SUCESSO! Miau!   ");
            System.out.println("========================================");
            System.out.println("Cliente: " + clienteSelecionado.getNome());
            System.out.println("Valor Total: R$ " + String.format("%.2f", valorAtual));
            System.out.println("========================================");
        }
    }
}