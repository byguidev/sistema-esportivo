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

    public void setId(long id) {
        this.id = id;
    }

    public Competicao getCompeticao() {
        return competicao;
    }

    public void setCompeticao(Competicao competicao) {
        this.competicao = competicao;
    }

    public Atleta getPrimeiroLugar() {
        return primeiroLugar;
    }

    public void setPrimeiroLugar(Atleta primeiroLugar) {
        this.primeiroLugar = primeiroLugar;
    }

    public Atleta getSegundoLugar() {
        return segundoLugar;
    }

    public void setSegundoLugar(Atleta segundoLugar) {
        this.segundoLugar = segundoLugar;
    }

    public Atleta getTerceiroLugar() {
        return terceiroLugar;
    }

    public void setTerceiroLugar(Atleta terceiroLugar) {
        this.terceiroLugar = terceiroLugar;
    }
}
