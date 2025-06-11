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
            // Se um erro acontecer ao salvar, vamos saber exatamente qual é.
            System.err.println("### ERRO FATAL AO SALVAR O ARQUIVO DE CLIENTES ###");
            e.printStackTrace();
        }
    }

    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        File arquivo = new File(ARQUIVO);

        // 1. Verificação inicial: se o arquivo não existe ou está vazio, não há o que ler.
        if (!arquivo.exists()) {
            return clientes; // Retorna a lista vazia, o que é esperado.
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
            // Isso pode acontecer se o arquivo for criado mas estiver vazio. É um "fim de arquivo" inesperado.
            System.err.println("Aviso: O arquivo '" + ARQUIVO + "' foi encontrado, mas está vazio ou incompleto.");
        } catch (IOException | ClassNotFoundException e) {
            // 2. Se qualquer outro erro acontecer durante a leitura, ele não será mais silencioso.
            System.err.println("### ERRO FATAL AO LER O ARQUIVO DE CLIENTES ###");
            System.err.println("O arquivo '" + ARQUIVO + "' pode estar corrompido.");
            e.printStackTrace(); // Esta linha é a mais importante para o diagnóstico!
        }
        return clientes;
    }
    
    // Os métodos abaixo não precisam de alteração
    
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