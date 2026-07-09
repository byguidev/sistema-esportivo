package br.edu.ifba.saj.ads.poo.model;

public class Inscricao {
    private static long contador = 0;
    private long id;
    private Atleta atleta;
    private Competicao competicao;

    public Inscricao(Atleta a, Competicao c) {
        this.id = ++contador;
        this.atleta = a;
        this.competicao = c;
    }

    public long getId() {
        return id;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public Competicao getCompeticao() {
        return competicao;
    }

    @Override
    public String toString() {
        return this.atleta.getNome();
    }
}
