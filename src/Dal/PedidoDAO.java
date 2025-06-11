package Dal;

import model.Cliente;
import model.Pedido;
import model.Produto;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PedidoDAO {

    private static final String ARQUIVO = "pedidos.dat";

    private void salvarTodos(List<Pedido> pedidos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeInt(pedidos.size());
            for (Pedido pedido : pedidos) {
                oos.writeInt(pedido.getId());
                oos.writeInt(pedido.getCliente().getId());
                oos.writeObject(pedido.getStatus()); // Alterado de writeUTF para writeObject
                
                oos.writeInt(pedido.getProdutos().size());
                for (Produto produto : pedido.getProdutos()) {
                    oos.writeInt(produto.getId());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar pedidos: " + e.getMessage());
        }
    }

    public List<Pedido> listarTodos() {
        List<Pedido> pedidos = new ArrayList<>();
        ClienteDAO clienteDAO = new ClienteDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            int numeroDePedidos = ois.readInt();
            for (int i = 0; i < numeroDePedidos; i++) {
                int pedidoId = ois.readInt();
                int clienteId = ois.readInt();
                String status = (String) ois.readObject(); // Alterado de readUTF para readObject
                
                Cliente cliente = clienteDAO.buscarPorId(clienteId);
                
                int numeroDeProdutos = ois.readInt();
                List<Produto> produtosDoPedido = new ArrayList<>();
                for (int j = 0; j < numeroDeProdutos; j++) {
                    int produtoId = ois.readInt();
                    Produto produto = produtoDAO.buscarPorId(produtoId);
                    if (produto != null) {
                        produtosDoPedido.add(produto);
                    }
                }
                
                if (cliente != null) {
                    Pedido pedido = new Pedido(pedidoId, cliente, produtosDoPedido);
                    pedido.setStatus(status);
                    pedidos.add(pedido);
                }
            }
        } catch (FileNotFoundException e) {
            // Ignora se o arquivo não existe.
        } catch (IOException | ClassNotFoundException e) { // Adicionado ClassNotFoundException
            System.err.println("Erro ao carregar pedidos: " + e.getMessage());
        }
        return pedidos;
    }

    // Métodos de salvar, atualizar, deletar e buscar (sem alterações, já estão corretos)

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
                break;
            }
        }
        salvarTodos(pedidos);
    }

    public void deletar(int id) {
        List<Pedido> pedidos = listarTodos();
        Iterator<Pedido> iterador = pedidos.iterator();
        while (iterador.hasNext()) {
            Pedido pedido = iterador.next();
            if (pedido.getId() == id) {
                iterador.remove();
                break;
            }
        }
        salvarTodos(pedidos);
    }
    
    public Pedido buscarPorId(int id) {
        List<Pedido> pedidos = listarTodos();
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                return pedido;
            }
        }
        return null;
    }
}