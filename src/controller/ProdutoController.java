package controller;

import Dal.ProdutoDAO;
import factory.ProdutoFactory;
import model.Produto;
import java.util.List;

public class ProdutoController {
    
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public void adicionarProduto(String nome, double preco) {
        List<Produto> produtosAtuais = produtoDAO.listarTodos();
        int maxId = 0;
        
        for (Produto p : produtosAtuais) {
            if (p.getId() > maxId) {
                maxId = p.getId();
            }
        }
        int proximoId = maxId + 1;

        Produto novoProduto = ProdutoFactory.criarProduto(proximoId, nome, preco);
        produtoDAO.salvar(novoProduto);
        System.out.println("Produto adicionado com sucesso!");
    }

    public List<Produto> listarTodos() {
        return produtoDAO.listarTodos();
    }

    public void listarProdutos() {
        List<Produto> produtos = produtoDAO.listarTodos();

        if (produtos == null || produtos.isEmpty()) {
            System.out.println("Nenhum produto registado.");
        } else {
            System.out.println("\n--- Lista de Produtos ---");
            for (Produto produto : produtos) {
                produto.listar();
            }
            System.out.println("-------------------------");
        }
    }

    public void removerProduto(int id) {
        produtoDAO.deletar(id);
        System.out.println("Remoção processada. Verifique a lista para confirmar.");
    }

    public Produto buscarProdutoPorId(int id) {
        return produtoDAO.buscarPorId(id);
    }
}
