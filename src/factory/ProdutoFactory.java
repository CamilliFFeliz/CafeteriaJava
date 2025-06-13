package factory;

import model.Produto;

public class ProdutoFactory {

    public static Produto criarProduto(int id, String nome, double preco) {

        if (nome == null || nome== "") {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }
        if (preco < 0) {
            throw new IllegalArgumentException("O Preço deve ser maior que 15,99.");
        }
        return new Produto(id, nome, preco);
    }
}