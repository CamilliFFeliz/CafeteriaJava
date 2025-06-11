package factory;

import model.Produto;

/**
 * Fábrica para criar instâncias de Produto.
 */
public class ProdutoFactory {

    /**
     * Cria e retorna um novo objeto Produto.
     */
    public static Produto criarProduto(int id, String nome, double preco) {
        return new Produto(id, nome, preco);
    }
}