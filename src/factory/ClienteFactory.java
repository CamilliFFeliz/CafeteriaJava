package factory;

import model.Cliente;

public class ClienteFactory {

    public static Cliente criarCliente(int id, String nome, String endereco, String telefone) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID deve ser um número positivo.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new IllegalArgumentException("O endereço não pode ser nulo ou vazio.");
        }
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new IllegalArgumentException("O telefone não pode ser nulo ou vazio.");
        }

        return new Cliente(id, nome, endereco, telefone);
    }
}