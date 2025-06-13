package Dal;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

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
            throw new RuntimeException("Erro ao salvar produtos no arquivo.", e);
        }
    }

    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) {
            return produtos;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            int numeroDeProdutos = ois.readInt();
            for (int i = 0; i < numeroDeProdutos; i++) {
                int id = ois.readInt();
                String nome = (String) ois.readObject();
                double preco = ois.readDouble();
                produtos.add(new Produto(id, nome, preco));
            }
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao carregar produtos do arquivo.", e);
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
        produtos.removeIf(produto -> produto.getId() == id);
        salvarTodos(produtos);
    }

    public Produto buscarPorId(int id) {
        return listarTodos().stream()
                .filter(produto -> produto.getId() == id)
                .findFirst()
                .orElse(null);
    }
}