package controller;

import Dal.ClienteDAO;
import factory.ClienteFactory;
import model.Cliente;
import java.util.List;

public class ClienteController {
    
    private ClienteDAO clienteDAO = new ClienteDAO();

    public void adicionarCliente(String nome, String endereco, String telefone) {
        List<Cliente> clientesAtuais = clienteDAO.listarTodos();
        int maxId = 0;
        
        for (Cliente c : clientesAtuais) {
            if (c.getId() > maxId) {
                maxId = c.getId();
            }
        }
        int proximoId = maxId + 1;

        Cliente novoCliente = ClienteFactory.criarCliente(proximoId, nome, endereco, telefone);
        clienteDAO.salvar(novoCliente);
        System.out.println("Cliente adicionado com sucesso!");
    }

    public List<Cliente> listarTodos() {
        return clienteDAO.listarTodos();
    }

    public void listarClientes() {
        List<Cliente> clientes = clienteDAO.listarTodos();

        if (clientes == null || clientes.isEmpty()) {
            System.out.println("Nenhum cliente registado.");
        } else {
            System.out.println("\n--- Lista de Clientes ---");
            for (Cliente cliente : clientes) {
                cliente.listar();
            }
            System.out.println("-------------------------");
        }
    }

    public void removerCliente(int id) {
        clienteDAO.deletar(id);
        System.out.println("Remoção processada. Verifique a lista para confirmar.");
    }

    public Cliente buscarClientePorId(int id) {
        return clienteDAO.buscarPorId(id);
    }
}
