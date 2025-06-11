package model;

public class Cliente extends Pessoa implements EntidadePersistivel {
    private int id;
    private String telefone;

    public Cliente(int id, String nome, String endereco, String telefone) {
        super(nome, endereco);
        this.id = id;
        this.telefone = telefone;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    @Override
    public void exibirInformacoes() {
        System.out.println("Cliente [ID: " + id + ", Nome: " + getNome() + ", Endereço: " + getEndereco() + ", Telefone: " + telefone + "]");
    }

    @Override
    public void salvar() {
        Log.salvarLog("Cliente salvo: " + getNome());
        System.out.println("Salvando cliente: " + getNome());
    }

    @Override
    public void deletar() {
        Log.salvarLog("Cliente deletado: " + getNome());
        System.out.println("Deletando cliente: " + getNome());
    }

    @Override
    public void listar() {
        exibirInformacoes();
    }
}
