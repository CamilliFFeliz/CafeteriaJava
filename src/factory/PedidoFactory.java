package factory;

import model.Cliente;
import model.Pedido;
import model.Produto;
import java.util.List;

public class PedidoFactory {

    public static Pedido criarPedido(int id, Cliente cliente, List<Produto> produtos) {
        return new Pedido(id, cliente, produtos);
    }
}
