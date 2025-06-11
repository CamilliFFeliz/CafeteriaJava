package Dal;

import model.Cliente;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClienteDAO {

    private static final String ARQUIVO = "clientes.dat";

    private void salvarTodos(List<Cliente> clientes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeInt(clientes.size());
            for (Cliente cliente : clientes) {
                oos.writeInt(cliente.getId());
                oos.writeObject(cliente.getNome());
                oos.writeObject(cliente.getEndereco());
                oos.writeObject(cliente.getTelefone());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar clientes: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        File arquivo = new File(ARQUIVO);

        if (!arquivo.exists()) {
            return clientes;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            int numeroDeClientes = ois.readInt();
            for (int i = 0; i < numeroDeClientes; i++) {
                int id = ois.readInt();
                String nome = (String) ois.readObject();
                String endereco = (String) ois.readObject();
                String telefone = (String) ois.readObject();
                clientes.add(new Cliente(id, nome, endereco, telefone));
            }
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar o arquivo de clientes: " + e.getMessage());
            e.printStackTrace();
        }
        return clientes;
    }

    public void salvar(Cliente cliente) {
        List<Cliente> clientes = listarTodos();
        clientes.add(cliente);
        salvarTodos(clientes);
    }

    public void atualizar(Cliente clienteAtualizado) {
        List<Cliente> clientes = listarTodos();
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == clienteAtualizado.getId()) {
                clientes.set(i, clienteAtualizado);
                break;
            }
        }
        salvarTodos(clientes);
    }

    public void deletar(int id) {
        List<Cliente> clientes = listarTodos();
        Iterator<Cliente> iterador = clientes.iterator();
        while (iterador.hasNext()) {
            Cliente cliente = iterador.next();
            if (cliente.getId() == id) {
                iterador.remove();
                break;
            }
        }
        salvarTodos(clientes);
    }

    public Cliente buscarPorId(int id) {
        List<Cliente> clientes = listarTodos();
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }
}
