package controller;

import model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    private List<Cliente> clientes = new ArrayList<>();

    public void adicionarCliente(Cliente c) {
        clientes.add(c);
        c.salvar();
    }

    public void listarClientes() {
        for (Cliente c : clientes) {
            c.listar();
        }
    }

    public void removerCliente(int id) {
        clientes.removeIf(c -> c.getId() == id);
        System.out.println("Cliente removido.");
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}