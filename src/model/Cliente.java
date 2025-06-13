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

    public String getInformacoes() {
        return "Cliente [ID: " + id + ", Nome: " + getNome() + ", Endereço: " + getEndereco() + ", Telefone: " + telefone + "]";
    }

    @Override
    public void exibirInformacoes() {
        Log.salvarLog("Informações exibidas do cliente: " + getInformacoes());
    }

    @Override
    public void salvar() {
        Log.salvarLog("Cliente salvo: " + getNome());
    }

    @Override
    public void deletar() {
        Log.salvarLog("Cliente deletado: " + getNome());
    }

    @Override
    public void listar() {
        exibirInformacoes();
    }
}
