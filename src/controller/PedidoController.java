package controller;

import Dal.PedidoDAO;
import factory.PedidoFactory;
import model.Cliente;
import model.Pedido;
import model.Produto;
import java.util.List;

/**
 * Controller para gerenciar as operações de Pedido.
 * Orquestra as ações entre a View de Pedido e o PedidoDAO.
 */
public class PedidoController {
    
    private PedidoDAO pedidoDAO = new PedidoDAO();

    /**
     * Cria um novo pedido no sistema.
     * Este método recebe os objetos Cliente e Produto já prontos, que a View
     * deve obter usando os outros controllers (ClienteController e ProdutoController).
     */
    public void criarPedido(Cliente cliente, List<Produto> produtos) {
        if (cliente == null || produtos == null || produtos.isEmpty()) {
            System.out.println("Erro: Não é possível criar um pedido sem um cliente e ao menos um produto.");
            return;
        }

        List<Pedido> pedidosAtuais = pedidoDAO.listarTodos();
        int maxId = 0;
        for (Pedido p : pedidosAtuais) {
            if (p.getId() > maxId) {
                maxId = p.getId();
            }
        }
        int proximoId = maxId + 1;

        Pedido novoPedido = PedidoFactory.criarPedido(proximoId, cliente, produtos);
        pedidoDAO.salvar(novoPedido);
        System.out.println("Pedido criado com sucesso!");
    }

    /**
     * Exibe todos os pedidos cadastrados.
     */
    public void listarPedidos() {
        List<Pedido> pedidos = pedidoDAO.listarTodos();
        if (pedidos == null || pedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.");
        } else {
            System.out.println("\n--- Lista de Pedidos ---");
            for (Pedido pedido : pedidos) {
                pedido.listar();
            }
            System.out.println("------------------------");
        }
    }
    
    /**
     * Atualiza o status de um pedido existente (ex: "Aberto" -> "Pago").
     */
    public void atualizarStatusPedido(int id, String novoStatus) {
        Pedido pedido = pedidoDAO.buscarPorId(id);
        if (pedido != null) {
            pedido.setStatus(novoStatus);
            pedidoDAO.atualizar(pedido);
            System.out.println("Status do pedido " + id + " atualizado para: " + novoStatus);
        } else {
            System.out.println("Pedido com ID " + id + " não encontrado.");
        }
    }

    /**
     * Remove um pedido do sistema com base no seu ID.
     */
    public void removerPedido(int id) {
        pedidoDAO.deletar(id);
        System.out.println("Remoção processada. Verifique a lista para confirmar.");
    }
}