package br.edu.ifba.saj.ads.poo.data;

import br.edu.ifba.saj.ads.poo.model.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioAtividades {
    private List<Competicao> listaCompeticoes = new ArrayList<>();
    private List<Atleta> listaAtletas = new ArrayList<>();
    private List<Inscricao> listaInscricoes = new ArrayList<>();
    private List<Resultado> listaResultados = new ArrayList<>();

    public void salvarCompeticao(Competicao c) {
        listaCompeticoes.add(c);
    }

    public boolean atualizarCompeticao(Competicao competicao) {
        for (int i = 0; i < listaCompeticoes.size(); i++) {
            if (listaCompeticoes.get(i).getId() == competicao.getId()) {
                listaCompeticoes.set(i, competicao);
                return true;
            }
        }

        return false;
    }

    public boolean removerCompeticao(long id) {
        return listaCompeticoes.removeIf(competicao -> competicao.getId() == id);
    }

    public java.util.Optional<Competicao> buscarCompeticaoPorId(long id) {
        return listaCompeticoes.stream().filter(competicao -> competicao.getId() == id).findFirst();
    }

    public void salvarAtleta(Atleta a) {
        listaAtletas.add(a);
    }

    public boolean atualizarAtleta(Atleta atleta) {
        for (int i = 0; i < listaAtletas.size(); i++) {
            if (listaAtletas.get(i).getId() == atleta.getId()) {
                listaAtletas.set(i, atleta);
                return true;
            }
        }

        return false;
    }

    public boolean removerAtleta(long id) {
        return listaAtletas.removeIf(atleta -> atleta.getId() == id);
    }

    public java.util.Optional<Atleta> buscarAtletaPorId(long id) {
        return listaAtletas.stream().filter(atleta -> atleta.getId() == id).findFirst();
    }

    public void salvarInscricao(Inscricao i) {
        listaInscricoes.add(i);
    }

    public boolean atualizarInscricao(Inscricao inscricao) {
        for (int i = 0; i < listaInscricoes.size(); i++) {
            if (listaInscricoes.get(i).getId() == inscricao.getId()) {
                listaInscricoes.set(i, inscricao);
                return true;
            }
        }

        return false;
    }

    public boolean removerInscricao(long id) {
        return listaInscricoes.removeIf(inscricao -> inscricao.getId() == id);
    }

    public java.util.Optional<Inscricao> buscarInscricaoPorId(long id) {
        return listaInscricoes.stream().filter(inscricao -> inscricao.getId() == id).findFirst();
    }

    public void salvarResultado(Resultado r) {
        listaResultados.add(r);
    }

    public boolean atualizarResultado(Resultado resultado) {
        for (int i = 0; i < listaResultados.size(); i++) {
            if (listaResultados.get(i).getId() == resultado.getId()) {
                listaResultados.set(i, resultado);
                return true;
            }
        }

        return false;
    }

    public boolean removerResultado(long id) {
        return listaResultados.removeIf(resultado -> resultado.getId() == id);
    }

    public java.util.Optional<Resultado> buscarResultadoPorId(long id) {
        return listaResultados.stream().filter(resultado -> resultado.getId() == id).findFirst();
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

    public List<Resultado> listarResultados() {
        return new ArrayList<>(listaResultados);
    }
}
