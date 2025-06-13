package controller;

import Dal.ClienteDAO;
import factory.ClienteFactory;
import model.Cliente;
import java.util.List;

public class ClienteController {
    
    private final ClienteDAO clienteDAO;

    public ClienteController(ClienteDAO clienteDAO) {
    this.clienteDAO = clienteDAO;
    }

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
    }

    public List<Cliente> listarTodos() {
        return clienteDAO.listarTodos();
    }


    public void listarClientes() {
        List<Cliente> clientes = clienteDAO.listarTodos();

        if (clientes != null && !clientes.isEmpty()) {
            for (Cliente cliente : clientes) {
                cliente.listar();
            }
        }
    }

    public void removerCliente(int id) {
        clienteDAO.deletar(id);
    }

    public Cliente buscarClientePorId(int id) {
        return clienteDAO.buscarPorId(id);
    }
}