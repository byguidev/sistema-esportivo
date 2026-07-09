package br.edu.ifba.saj.ads.poo.model;

public class Resultado {
    private static long contador = 0;
    private long id;
    private Competicao competicao;
    private Atleta primeiroLugar;
    private Atleta segundoLugar;
    private Atleta terceiroLugar;

    public Resultado(Competicao competicao, Atleta primeiroLugar, Atleta segundoLugar, Atleta terceiroLugar) {
        this.id = ++contador;
        this.competicao = competicao;
        this.primeiroLugar = primeiroLugar;
        this.segundoLugar = segundoLugar;
        this.terceiroLugar = terceiroLugar;
    }

    public long getId() {
        return id;
    }

    public Competicao getCompeticao() {
        return competicao;
    }

    public Atleta getPrimeiroLugar() {
        return primeiroLugar;
    }

    public Atleta getSegundoLugar() {
        return segundoLugar;
    }

    public Atleta getTerceiroLugar() {
        return terceiroLugar;
    }
}
