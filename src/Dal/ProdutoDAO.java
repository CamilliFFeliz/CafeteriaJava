public class ProdutoDAO extends DaoBase<Produto> {

    public ProdutoDAO() {
        super("produtos");
    }

    @Override
    public void validarDados(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }
        if (produto.getPreco() <= 0) {
            throw new IllegalArgumentException("Preço do produto deve ser positivo");
        }
    }

    public void adicionarProduto(Produto novoProduto) {
        validarDados(novoProduto);
        List<Produto> produtos = carregarDados();
        produtos.add(novoProduto);
        salvarDados(produtos);
    }
}