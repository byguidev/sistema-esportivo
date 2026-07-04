package br.edu.ifba.saj.ads.poo.data;

import br.edu.ifba.saj.ads.poo.model.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioAtividades {
    private List<Competicao> listaCompeticoes = new ArrayList<>();
    private List<Atleta> listaAtletas = new ArrayList<>();
    private List<Inscricao> listaInscricoes = new ArrayList<>();

    public void salvarCompeticao(Competicao c) {
        listaCompeticoes.add(c);
    }

    public void salvarAtleta(Atleta a) {
        listaAtletas.add(a);
    }

    public void salvarInscricao(Inscricao i) {
        listaInscricoes.add(i);
    }

    public List<Competicao> listarCompeticoes() {
        return new ArrayList<>(listaCompeticoes);
    }

    public List<Atleta> listarAtletas() {
        return new ArrayList<>(listaAtletas);
    }

    public List<Inscricao> listarInscricoes() {
        return new ArrayList<>(listaInscricoes);
    }
}
