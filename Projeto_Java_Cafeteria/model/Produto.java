package model;

public class Produto implements Persistivel {
    private int id;
    private String nome;
    private double preco;

    public Produto(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    @Override
    public void salvar() {
        System.out.println("Produto " + nome + " salvo.");
        Log.salvarLog("Produto salvo: " + nome);
    }

    @Override
    public void deletar() {
        System.out.println("Produto " + nome + " deletado.");
        Log.salvarLog("Produto deletado: " + nome);
    }

    @Override
    public void listar() {
        System.out.println("Produto [ID: " + id + ", Nome: " + nome + ", Preço: R$ " + preco + "]");
    }

    @Override
    public String toString() {
        return "Produto: " + nome + " - Preço: R$ " + preco;
    }

    public void salvar(String usuario) {
        System.out.println("Produto " + nome + " salvo pelo usuário " + usuario);
        Log.salvarLog("Produto salvo: " + nome + " pelo usuário " + usuario);
    }
}