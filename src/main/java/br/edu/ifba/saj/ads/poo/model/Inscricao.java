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

    public void setId(long id) {
        this.id = id;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public void setAtleta(Atleta atleta) {
        this.atleta = atleta;
    }

    public Competicao getCompeticao() {
        return competicao;
    }

    public void setCompeticao(Competicao competicao) {
        this.competicao = competicao;
    }

    @Override
    public String toString() {
        return this.atleta.getNome() + " - " + this.competicao.getNome();
    }
}
