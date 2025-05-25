package model;

import java.io.Serializable;

public abstract class Entidade implements Serializable {
    protected int id;

    public Entidade(int id) {
        this.id = id;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    // Método abstrato que será implementado nas subclasses
    public abstract void salvar();
}