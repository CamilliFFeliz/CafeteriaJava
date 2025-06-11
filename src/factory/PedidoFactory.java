package factory;

import model.Cliente;
import model.Pedido;
import model.Produto;
import java.util.List;

/**
 * Fábrica para criar instâncias de Pedido.
 */
public class PedidoFactory {

    /**
     * Cria e retorna um novo objeto Pedido.
     */
    public static Pedido criarPedido(int id, Cliente cliente, List<Produto> produtos) {
        return new Pedido(id, cliente, produtos);
    }
}