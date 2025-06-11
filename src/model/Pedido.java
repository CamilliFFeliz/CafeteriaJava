package model;

import java.util.ArrayList;
import java.util.List;

public class Pedido implements EntidadePersistivel {
    private int id;
    private Cliente cliente;
    private List<Produto> produtos;
    private String status;

    public Pedido(int id, Cliente cliente, List<Produto> produtos) {
        this.id = id;
        this.cliente = cliente;
        this.produtos = new ArrayList<>(produtos);
        this.status = "Aberto";
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public List<Produto> getProdutos() { return produtos; }
    public void setProdutos(List<Produto> produtos) { this.produtos = produtos; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getValorTotal() {
        double total = 0.0;
        if (this.produtos != null) {
            for (Produto produto : this.produtos) {
                total += produto.getPreco();
            }
        }
        return total;
    }

    @Override
    public void salvar() {
        Log.salvarLog("Pedido salvo: ID " + this.id + " para o cliente " + this.cliente.getNome());
    }

    @Override
    public void deletar() {
        Log.salvarLog("Pedido deletado: ID " + this.id);
    }

    @Override
    public void listar() {
        System.out.println("\n--- Pedido ID: " + this.id + " ---");
        System.out.println("Status: " + this.status);
        if (this.cliente != null) {
            System.out.println("Cliente: " + this.cliente.getNome() + " (ID: " + this.cliente.getId() + ")");
        } else {
            System.out.println("Cliente: N/A");
        }
        System.out.println("Itens do Pedido:");
        if (this.produtos != null && !this.produtos.isEmpty()) {
            for (Produto produto : this.produtos) {
                System.out.println("  - " + produto.getNome() + " | R$ " + String.format("%.2f", produto.getPreco()));
            }
        } else {
            System.out.println("  (Nenhum item no pedido)");
        }
        System.out.println("Valor Total: R$ " + String.format("%.2f", getValorTotal()));
        System.out.println("--------------------");
    }
}
