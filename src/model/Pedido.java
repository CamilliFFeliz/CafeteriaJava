package model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private Cliente cliente;
    private List<Produto> produtos;
    private String status;

    public Pedido(int id, Cliente cliente, List<Produto> produtos) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID do pedido deve ser um número positivo.");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo.");
        }
        if (produtos == null || produtos.isEmpty()) {
            throw new IllegalArgumentException("A lista de produtos não pode ser nula ou vazia.");
        }
        this.id = id;
        this.cliente = cliente;
        this.produtos = new ArrayList<>(produtos);
        this.status = "Aberto";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getValorTotal() {
        return this.produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();
    }
    
    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente=" + cliente.getNome() +
                ", produtos=" + produtos.size() + " itens" +
                ", status='" + status + '\'' +
                ", valorTotal=" + String.format("%.2f", getValorTotal()) +
                '}';
    }
}