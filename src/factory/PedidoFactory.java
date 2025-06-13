package factory;

import model.Cliente;
import model.Pedido;
import model.Produto;
import java.util.List;

public class PedidoFactory {

    public static Pedido criarPedido(int id, Cliente cliente, List<Produto> produtos) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID do pedido deve ser um número positivo.");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo.");
        }
        if (produtos == null || produtos.isEmpty()) {
            throw new IllegalArgumentException("A lista de produtos não pode ser nula ou vazia.");
        }
        
        return new Pedido(id, cliente, produtos);
    }
}