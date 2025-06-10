package dal;

import java.io.*;
import java.util.*;
import model.Cliente;

public class ClienteDAO {

    private static final String CAMINHO = "./Dados";
    private static final String ARQUIVO = CAMINHO + "/usuarios.ser";

    public static void adicionarCliente(Cliente cliente) {
        List<Cliente> clientes = carregarClientes();
        clientes.add(cliente);
        salvarClientes(clientes);
    }

    public static void salvarClientes(List<Cliente> clientes) {
        try {
            File diretorio = new File(CAMINHO);
            diretorio.mkdirs();

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
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id) {
                clientes.set(i, clienteAtualizado);
                salvarClientes(clientes);
                return;
            }
        }
        System.out.println("Cliente com id " + id + " não encontrado.");
    }

    public static void excluirCliente(int id) {
        List<Cliente> clientes = carregarClientes();
        boolean removed = clientes.removeIf(c -> c.getId() == id);
        if (removed) {
            salvarClientes(clientes);
        } else {
            System.out.println("Cliente com id " + id + " não encontrado.");
        }
    }
}
