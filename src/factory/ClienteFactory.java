package factory;

import model.Cliente;

/**
 * Fábrica para criar instâncias de Cliente.
 * Centraliza a lógica de criação de objetos Cliente, desacoplando o Controller
 * dos detalhes da construção do objeto.
 */
public class ClienteFactory {

    /**
     * Cria e retorna um novo objeto Cliente com os dados fornecidos.
     */
    public static Cliente criarCliente(int id, String nome, String endereco, String telefone) {
        return new Cliente(id, nome, endereco, telefone);
    }
}