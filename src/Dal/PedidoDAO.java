package Dal;

import model.Cliente;
import model.Pedido;
import model.Produto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    private static final String ARQUIVO = "pedidos.dat";
    private final ClienteDAO clienteDAO;
    private final ProdutoDAO produtoDAO;

    public PedidoDAO(ClienteDAO clienteDAO, ProdutoDAO produtoDAO) {
        this.clienteDAO = clienteDAO;
        this.produtoDAO = produtoDAO;
    }

    public List<Pedido> listarTodos() {
        List<Pedido> pedidos = new ArrayList<>();
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) {
            return pedidos;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            int numeroDePedidos = ois.readInt();
            for (int i = 0; i < numeroDePedidos; i++) {
                int pedidoId = ois.readInt();
                int clienteId = ois.readInt(); // CORRIGIDO: para readInt
                String status = (String) ois.readObject();
                Cliente cliente = clienteDAO.buscarPorId(clienteId);

                int numeroDeProdutos = ois.readInt();
                List<Produto> produtosDoPedido = new ArrayList<>();
                for (int j = 0; j < numeroDeProdutos; j++) {
                    int produtoId = ois.readInt(); // CORRIGIDO: para readInt
                    Produto produto = produtoDAO.buscarPorId(produtoId);
                    if (produto != null) {
                        produtosDoPedido.add(produto);
                    }
                }

                if (cliente != null && !produtosDoPedido.isEmpty()) {
                    Pedido pedido = new Pedido(pedidoId, cliente, produtosDoPedido);
                    pedido.setStatus(status);
                    pedidos.add(pedido);
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Falha ao ler o arquivo de pedidos. O arquivo pode estar corrompido.", e);
        }
        return pedidos;
    }

    private void salvarTodos(List<Pedido> pedidos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeInt(pedidos.size());
            for (Pedido pedido : pedidos) {
                oos.writeInt(pedido.getId());
                oos.writeInt(pedido.getCliente().getId()); // CORRIGIDO: para writeInt
                oos.writeObject(pedido.getStatus());
                oos.writeInt(pedido.getProdutos().size());
                for (Produto produto : pedido.getProdutos()) {
                    oos.writeInt(produto.getId()); // CORRIGIDO: para writeInt
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Falha ao salvar o arquivo de pedidos.", e);
        }
    }

    // O restante da classe (salvar, atualizar, deletar, buscarPorId) permanece igual.
    public void salvar(Pedido pedido) {
        List<Pedido> pedidos = listarTodos();
        pedidos.add(pedido);
        salvarTodos(pedidos);
    }
    
    public void atualizar(Pedido pedidoAtualizado) {
        List<Pedido> pedidos = listarTodos();
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getId() == pedidoAtualizado.getId()) {
                pedidos.set(i, pedidoAtualizado);
                salvarTodos(pedidos);
                return;
            }
        }
        throw new RuntimeException("Pedido para atualizar não encontrado: ID " + pedidoAtualizado.getId());
    }

    public void deletar(int id) {
        List<Pedido> pedidos = listarTodos();
        boolean removido = pedidos.removeIf(p -> p.getId() == id);
        if (removido) {
            salvarTodos(pedidos);
        } else {
            throw new RuntimeException("Pedido para deletar não encontrado: ID " + id);
        }
    }

    public Pedido buscarPorId(int id) {
        return listarTodos().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
}