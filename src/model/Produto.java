package model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Produto implements EntidadePersistivel, Externalizable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String nome;
    private double preco;

    // CONSTRUTOR PÚBLICO VAZIO (OBRIGATÓRIO)
    public Produto() {}
    
    public Produto(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(id);
        out.writeUTF(nome);
        out.writeDouble(preco);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = in.readInt();
        nome = in.readUTF();
        preco = in.readDouble();
    }
    
    // Getters, setters e outros métodos permanecem os mesmos...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    @Override
    public void salvar() { /* ... */ }
    @Override
    public void deletar() { /* ... */ }
    @Override
    public void listar() { System.out.println("Produto [ID: " + id + ", Nome: " + nome + ", Preço: R$ " + String.format("%.2f", preco) + "]"); }
    @Override
    public String toString() { return "Produto: " + nome + " - Preço: R$ " + String.format("%.2f", preco); }
    public void salvar(String usuario) { /* ... */ }
}