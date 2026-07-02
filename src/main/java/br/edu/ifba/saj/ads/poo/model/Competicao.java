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

    public String getNome() {
        return nome;
    }

    public LocalDate getData() {
        return data;
    }

    public int getLimite() {
        return limiteParticipantes;
    }
}