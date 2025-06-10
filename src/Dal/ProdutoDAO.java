package dal;

import java.io.*;
import java.util.*;
import model.Produto;

public abstract class ProdutoDAO {

    private static final String CAMINHO = "./Dados";
    private static final String ARQUIVO = CAMINHO + "/produtos.ser";

    public static void adicionarProduto(Produto produto) {
        List<Produto> produtos = carregarProdutos();
        produtos.add(produto);
        salvarProdutos(produtos);
    }

    public static void salvarProdutos(List<Produto> produtos) {
        try {
            File diretorio = new File(CAMINHO);
            diretorio.mkdirs();

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
                oos.writeObject(produtos);
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    public static void atualizarProduto(List<Produto> produtos) {
        try {
            File diretorio = new File(CAMINHO);
            diretorio.mkdirs();

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
                oos.writeObject(produtos);
            }
        } catch (IOException e) {
            System.out.println("Erro ao atualizar produtos: " + e.getMessage());
        }
    }

    public static List<Produto> carregarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        File arquivo = new File(ARQUIVO);
        
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
                produtos = (List<Produto>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erro ao carregar produtos: " + e.getMessage());
            }
        }
        return produtos;
    }

    public static void editarProduto(int id, Produto produtoAtualizado) {
        List<Produto> produtos = carregarProdutos();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == id) {
                produtos.set(i, produtoAtualizado);
                break;
            }
        }
        salvarProdutos(produtos);
    }

    public static void excluirProduto(int id) {
        List<Produto> produtos = carregarProdutos();
        produtos.removeIf(produto -> produto.getId() == id);
        salvarProdutos(produtos);
    }

    public static Produto buscarPorId(int id) {
        List<Produto> produtos = carregarProdutos();
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }
}