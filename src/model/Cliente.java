package model;

// Nenhuma implementação especial. Apenas herda de Pessoa e implementa a interface do projeto.
public class Cliente extends Pessoa implements EntidadePersistivel {
    private int id;
    private String telefone;

    public Cliente(int id, String nome, String endereco, String telefone) {
        super(nome, endereco);
        this.id = id;
        this.telefone = telefone;
    }
    
    // Getters, setters e métodos da interface...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    @Override
    public void exibirInformacoes() { /* ... */ }
    @Override
    public void salvar() { /* ... */ }
    @Override
    public void deletar() { /* ... */ }
    @Override
    public void listar() { /* ... */ }
}