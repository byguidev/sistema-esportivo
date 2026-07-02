package br.edu.ifba.saj.ads.poo.data;

import br.edu.ifba.saj.ads.poo.model.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioAtividades {
    private List<Competicao> listaCompeticoes = new ArrayList<>();
    private List<Inscricao> listaInscricoes = new ArrayList<>();

    public void salvarCompeticao(Competicao c) {
        listaCompeticoes.add(c);
    }

    public List<Competicao> listarCompeticoes() {
        return new ArrayList<>(listaCompeticoes);
    }

    public void salvarInscricao(Inscricao i) {
        listaInscricoes.add(i);
    }

    public List<Inscricao> listarInscricoes() {
        return new ArrayList<>(listaInscricoes);
    }
}
