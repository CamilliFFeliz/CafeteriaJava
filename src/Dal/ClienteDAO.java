package dal;

import java.io.*;
import java.util.*;
import model.Cliente;

public class ClienteDAO {

    private static final String CAMINHO = "./Dados";
    private static final String ARQUIVO = CAMINHO + "/clientes.ser";  // Corrigido o caminho

    public static void adicionarCliente(Cliente cliente) {
        List<Cliente> clientes = carregarClientes();
        clientes.add(cliente);
        salvarClientes(clientes);
    }

    public static void salvarClientes(List<Cliente> clientes) {
        try {
            File diretorio = new File(CAMINHO);
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
                oos.writeObject(clientes);
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    public static List<Cliente> carregarClientes() {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            return (List<Cliente>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar clientes: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void editarCliente(int id, Cliente clienteAtualizado) {
        List<Cliente> clientes = carregarClientes();
        boolean encontrado = false;
        
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id) {
                clientes.set(i, clienteAtualizado);
                encontrado = true;
                break;
            }
        }
        
        if (encontrado) {
            salvarClientes(clientes);
        } else {
            System.out.println("Cliente com id " + id + " não encontrado.");
        }
    }

    public static void excluirCliente(int id) {
        List<Cliente> clientes = carregarClientes();
        boolean removido = false;
        
        for (int i = clientes.size() - 1; i >= 0; i--) {
            if (clientes.get(i).getId() == id) {
                clientes.remove(i);
                removido = true;
                break; 
            }
        }
        
        if (removido) {
            salvarClientes(clientes);
            System.out.println("Cliente removido com sucesso.");
        } else {
            System.out.println("Cliente com ID " + id + " não encontrado.");
        }
    }

    public static Cliente buscarPorId(int id) {
        List<Cliente> clientes = carregarClientes();
        
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        
        return null;
    }

    public static void listarTodosClientes() {
        List<Cliente> clientes = carregarClientes();
        
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        
        System.out.println("\n--- LISTA DE CLIENTES ---");
        for (Cliente cliente : clientes) {
            System.out.println("ID: " + cliente.getId() + 
                             " | Nome: " + cliente.getNome() +
                             " | Endereço: " + cliente.getEndereco() +
                             " | Telefone: " + cliente.getTelefone());
        }
    }
}