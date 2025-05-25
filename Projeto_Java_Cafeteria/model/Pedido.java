package model;

import java.util.ArrayList;
import java.util.List;

public class Pedido implements Persistivel {
    private int id;
    private Cliente cliente;
    private List<Produto> produtos;

    public Pedido(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.produtos = new ArrayList<>();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<Produto> getProdutos() { return produtos; }

    public void adicionarProduto(Produto p) {
        produtos.add(p);
    }

    @Override
    public void salvar() {
        System.out.println("Pedido ID " + id + " para o cliente " + cliente.getNome() + " salvo.");
        Log.salvarLog("Pedido salvo: ID " + id + ", Cliente " + cliente.getNome());
    }

    @Override
    public void deletar() {
        System.out.println("Pedido ID " + id + " deletado.");
        Log.salvarLog("Pedido deletado: ID " + id);
    }

    @Override
    public void listar() {
        System.out.println("Pedido ID: " + id + ", Cliente: " + cliente.getNome());
        System.out.println("Produtos:");
        for (Produto p : produtos) {
            System.out.println("- " + p.toString());
        }
    }
}