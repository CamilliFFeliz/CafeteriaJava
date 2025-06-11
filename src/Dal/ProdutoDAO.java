package Dal;

import model.Produto;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProdutoDAO {

    private static final String ARQUIVO = "produtos.dat";

    private void salvarTodos(List<Produto> produtos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeInt(produtos.size());
            for (Produto produto : produtos) {
                oos.writeInt(produto.getId());
                oos.writeObject(produto.getNome());
                oos.writeDouble(produto.getPreco());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            int numeroDeProdutos = ois.readInt();
            for (int i = 0; i < numeroDeProdutos; i++) {
                int id = ois.readInt();
                String nome = (String) ois.readObject();
                double preco = ois.readDouble();
                produtos.add(new Produto(id, nome, preco));
            }
        } catch (FileNotFoundException e) {
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar produtos: " + e.getMessage());
        }
        return produtos;
    }

    public void salvar(Produto produto) {
        List<Produto> produtos = listarTodos();
        produtos.add(produto);
        salvarTodos(produtos);
    }

    public void atualizar(Produto produtoAtualizado) {
        List<Produto> produtos = listarTodos();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == produtoAtualizado.getId()) {
                produtos.set(i, produtoAtualizado);
                break;
            }
        }
        salvarTodos(produtos);
    }

    public void deletar(int id) {
        List<Produto> produtos = listarTodos();
        Iterator<Produto> iterador = produtos.iterator();
        while (iterador.hasNext()) {
            Produto produto = iterador.next();
            if (produto.getId() == id) {
                iterador.remove();
                break;
            }
        }
        salvarTodos(produtos);
    }

    public Produto buscarPorId(int id) {
        List<Produto> produtos = listarTodos();
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }
}
