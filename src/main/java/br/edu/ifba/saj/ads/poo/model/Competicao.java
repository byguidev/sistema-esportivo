package br.edu.ifba.saj.ads.poo.model;

import java.time.LocalDate;

public class Competicao {
    private static long contador = 0;
    private long id;
    private String nome;
    private LocalDate data;
    private int limiteParticipantes;

    public Competicao(String nome, LocalDate data, int limiteParticipantes) {
        this.id = ++contador;
        this.nome = nome;
        this.data = data;
        this.limiteParticipantes = limiteParticipantes;
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getLimite() {
        return limiteParticipantes;
    }

    public void setLimite(int limiteParticipantes) {
        this.limiteParticipantes = limiteParticipantes;
    }

    @Override
    public String toString() {
        return this.getNome();
    }
}