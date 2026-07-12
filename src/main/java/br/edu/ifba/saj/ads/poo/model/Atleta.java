package br.edu.ifba.saj.ads.poo.model;

public class Atleta {
    private static long contador = 0;
    private long id;
    private String nome;
    private String categoria;

    public Atleta(String nome, String categoria) {
        this.id = ++contador;
        this.nome = nome;
        this.categoria = categoria;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return this.getNome();
    }
}