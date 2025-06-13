package controller;

import Dal.PedidoDAO;
import factory.PedidoFactory;
import model.Cliente;
import model.Pedido;
import model.Produto;
import java.util.List;

public class PedidoController {

    private final PedidoDAO pedidoDAO;

    public PedidoController(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    public void criarPedido(Cliente cliente, List<Produto> produtos) {
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
    }

    public List<Pedido> getPedidos() {
        return pedidoDAO.listarTodos();
    }
    
    public Pedido getPedidoPorId(int id) {
        return pedidoDAO.buscarPorId(id);
    }

    public void atualizarStatusPedido(int id, String novoStatus) {
        Pedido pedido = pedidoDAO.buscarPorId(id);
        if (pedido != null) {
            pedido.setStatus(novoStatus);
            pedidoDAO.atualizar(pedido);
        } else {
            throw new RuntimeException("Pedido com ID " + id + " não encontrado para atualizar status.");
        }
    }

    public void removerPedido(int id) {
        pedidoDAO.deletar(id);
    }
}