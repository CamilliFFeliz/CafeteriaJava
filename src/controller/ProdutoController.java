package controller;

import Dal.ProdutoDAO;
import factory.ProdutoFactory;
import java.util.List;
import model.Produto;

public class ProdutoController {
    
    private final ProdutoDAO produtoDAO;
    
    public ProdutoController(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    public void adicionarProduto(String nome, double preco) {
        List<Produto> produtosAtuais = produtoDAO.listarTodos();
        int proximoId = calcularProximoId(produtosAtuais);
        
        Produto novoProduto = ProdutoFactory.criarProduto(proximoId, nome, preco);
        produtoDAO.salvar(novoProduto);
    }

    private int calcularProximoId(List<Produto> produtos) {
        if (produtos == null || produtos.isEmpty()) {
            return 1;
        }
        int maxId = 0;
        for (Produto p : produtos) {
            if (p.getId() > maxId) {
                maxId = p.getId();
            }
        }
        return maxId + 1;
    }

    public List<Produto> listarTodos() {
        return produtoDAO.listarTodos();
    }

    public void listarProdutos() {
        List<Produto> produtos = produtoDAO.listarTodos();
        
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto registrado.");
        } else {
            System.out.println("\n--- Lista de Produtos ---");
            produtos.forEach(Produto::listar);
            System.out.println("-------------------------");
        }
    }

    public boolean removerProduto(int id) {
        Produto produto = produtoDAO.buscarPorId(id);
        if (produto != null) {
            produtoDAO.deletar(id);
            return true;
        }
        return false;
    }

    public Produto buscarProdutoPorId(int id) {
        return produtoDAO.buscarPorId(id);
    }
}