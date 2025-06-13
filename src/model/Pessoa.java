package model;

public abstract class Pessoa {
    protected String nome;
    protected String endereco;

    public Pessoa(String nome, String endereco) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new IllegalArgumentException("O endereço não pode ser nulo ou vazio.");
        }
        this.nome = nome;
        this.endereco = endereco;
    }

    protected Pessoa() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "nome='" + nome + '\'' + ", endereco='" + endereco + '\'';
    }
    
    public abstract void exibirInformacoes();
}
