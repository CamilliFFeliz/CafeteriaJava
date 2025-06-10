package dal;

import java.io.*;
import java.util.*;
import model.Pedido;

public abstract class PedidoDao {

    private static final String CAMINHO = "./Dados";
    private static final String ARQUIVO = CAMINHO + "/pedidos.ser";

    public static void salvarPedidos(List<Pedido> pedidos) {
        try {
            File diretorio = new File(CAMINHO);
            diretorio.mkdirs();

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
                oos.writeObject(pedidos);
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar pedidos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Pedido> carregarPedidos() {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            return (List<Pedido>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar pedidos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void adicionarPedido(Pedido novoPedido) {
        List<Pedido> pedidos = carregarPedidos();
        pedidos.add(novoPedido);
        salvarClientes(pedidos);
    }

    public static void removerPedido(int id) {
        List<Pedido> pedidos = carregarPedidos();
        pedidos.removeIf(c -> c.getId() == id);
        salvarPedidos(pedidos);
    }
}
