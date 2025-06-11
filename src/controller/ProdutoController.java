package controller;

import Dal.ProdutoDAO;
import factory.ProdutoFactory;
import model.Produto;
import java.util.List;

/**
 * Controller para gerenciar as operações de Produto.
 * Orquestra as ações entre a View de Produto e o ProdutoDAO.
 */
public class ProdutoController {
    
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    /**
     * Adiciona um novo produto ao sistema.
     * Calcula o próximo ID, usa a factory para criar o objeto e pede ao DAO para salvar.
     */
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

    /**
     * Exibe todos os produtos cadastrados.
     * Busca a lista no DAO e a imprime na tela.
     */
    public void listarProdutos() {
        List<Produto> produtos = produtoDAO.listarTodos();

        if (produtos == null || produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            System.out.println("\n--- Lista de Produtos ---");
            for (Produto produto : produtos) {
                produto.listar();
            }
            System.out.println("-------------------------");
        }
    }

    /**
     * Remove um produto do sistema com base no seu ID.
     */
    public void removerProduto(int id) {
        produtoDAO.deletar(id);
        System.out.println("Remoção processada. Verifique a lista para confirmar.");
    }

    /**
     * Busca e retorna um objeto Produto pelo seu ID.
     */
    public Produto buscarProdutoPorId(int id) {
        return produtoDAO.buscarPorId(id);
    }
}