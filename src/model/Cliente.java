package model;
import model.Interfaces;

public class Cliente extends Pessoa implements Interfaces {

    private int id;
    private String telefone;

    public Cliente(int id, String nome, String endereco, String telefone) {
        super(nome, endereco);
        this.id = id;
        this.telefone = telefone;
    }

    public int getId() { return id;}
    public String getTelefone() { return telefone;}

    public void setId(int id) { this.id = id; }
    public void setTelefone(String telefone) { this.telefone = telefone;}

    @Override
    public void salvar() {
        System.out.println("Cliente " + nome + " salvo com sucesso.");
        Log.salvarLog("Cliente salvo: " + nome);
    }
    @Override
    public void deletar() {
        System.out.println("Cliente " + nome + " deletado.");
        Log.salvarLog("Cliente deletado: " + nome);
    }
    @Override
    public void listar() {
        System.out.println("Cliente [ID: " + id + ", Nome: " + nome + ", Endereço: " + endereco + ", Telefone: " + telefone + "]");
    }
}