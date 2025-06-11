package factory;

import model.Cliente;

public class ClienteFactory {

    public static Cliente criarCliente(int id, String nome, String endereco, String telefone) {
        return new Cliente(id, nome, endereco, telefone);
    }
}
