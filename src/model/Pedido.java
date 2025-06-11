package model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

public class Pedido implements EntidadePersistivel, Externalizable {
    private static final long serialVersionUID = 1L;
    private int id;
    private Cliente cliente;
    private List<Produto> produtos;
    private String status;
    
    // CONSTRUTOR PÚBLICO VAZIO (OBRIGATÓRIO)
    public Pedido() {}

    public Pedido(int id, Cliente cliente, List<Produto> produtos) {
        this.id = id;
        this.cliente = cliente;
        this.produtos = produtos;
        this.status = "Aberto";
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(id);
        out.writeObject(cliente);     // Salva o objeto Cliente
        out.writeObject(produtos);    // Salva a lista de objetos Produto
        out.writeUTF(status);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = in.readInt();
        cliente = (Cliente) in.readObject(); // Lê o objeto Cliente
        produtos = (List<Produto>) in.readObject(); // Lê a lista de objetos Produto
        status = in.readUTF();
    }

    // Getters, setters e outros métodos permanecem os mesmos...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public List<Produto> getProdutos() { return produtos; }
    public void setProdutos(List<Produto> produtos) { this.produtos = produtos; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public void salvar() { /* ... */ }
    @Override
    public void deletar() { /* ... */ }
    @Override
    public void listar() { /* ... */ }
}