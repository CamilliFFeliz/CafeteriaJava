package model;

// Nenhuma implementação especial necessária. Apenas a classe abstrata.
public abstract class Pessoa {
    protected String nome;
    protected String endereco;

    public Pessoa(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }
    
    protected Pessoa() {} // Construtor para as classes filhas

    // Getters e Setters...
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public abstract void exibirInformacoes();
}