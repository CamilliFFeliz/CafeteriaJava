package factory;

import model.Pedido;

public class PedidoFactory {
    public static Pedido criarPedido(int id, Cliente cliente, String endereco, List<Produto> produtos) {
        return new Pedido(id, cliente, produtos);
    }
}