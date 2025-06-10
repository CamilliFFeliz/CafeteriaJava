public abstract class DaoBase<T> { 
    protected String caminho;
    protected String arquivo;

    public DaoBase(String entidade) {
        this.caminho = "./Dados/";
        this.arquivo = this.caminho + entidade + ".ser";
    }

    protected void salvarDados(List<T> dados) {
        try {
            File diretorio = new File(caminho);
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
                oos.writeObject(dados);
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    protected List<T> carregarDados() {
        File arquivo = new File(this.arquivo);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public abstract void validarDados(T item);
}

public class PedidoDao extends DaoBase<Pedido> {  
    
    public PedidoDao() {
        super("pedidos");  
    }

    @Override
    public void validarDados(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }
        if (pedido.getCliente() == null) {
            throw new IllegalArgumentException("Pedido deve ter um cliente associado");
        }
    }

    public void adicionarPedido(Pedido novoPedido) {
        validarDados(novoPedido);  
        List<Pedido> pedidos = carregarDados();
        pedidos.add(novoPedido);
        salvarDados(pedidos);
    }

}