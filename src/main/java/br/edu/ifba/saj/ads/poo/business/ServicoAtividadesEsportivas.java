package br.edu.ifba.saj.ads.poo.business;

import br.edu.ifba.saj.ads.poo.data.RepositorioAtividades;
import br.edu.ifba.saj.ads.poo.model.*;
import java.util.List;

public class ServicoAtividadesEsportivas {
    private RepositorioAtividades repositorio;

    public ServicoAtividadesEsportivas(RepositorioAtividades repositorio) {
        this.repositorio = repositorio;
    }

    public boolean temVaga(Competicao c, List<Inscricao> i) {
        long idCompeticao = c.getId();
        int limiteCompeticao = c.getLimite();
        long totalParticipantes = i.stream().filter(ins -> ins.getCompeticao().getId() == idCompeticao).count();

        return totalParticipantes < limiteCompeticao;
    }

    public boolean inscricaoRepetida(Competicao c, List<Inscricao> listaIns, Atleta a) {
        long idCompeticao = c.getId();
        List<Inscricao> inscricoesC = listaIns.stream().filter(ins -> ins.getCompeticao().getId() == idCompeticao).toList();
        long incricoesRepetidas = inscricoesC.stream().filter(ins -> ins.getAtleta().getId() == a.getId()).count();

        return incricoesRepetidas > 0;
    }

    public void criarCompeticao(Competicao c) throws Exception {
        if (c.getNome() == null || c.getNome().isEmpty()) {
            throw new Exception("Nome da competição é obrigatório.");
        }

        if (c.getLimite() <= 0) {
            throw new Exception("Limite de participantes deve ser maior que 0.");
        }

        repositorio.salvarCompeticao(c);
    }

    public void criarAtleta(Atleta a) throws Exception {
        if (a.getNome() == null || a.getNome().isEmpty()) {
            throw new Exception("Nome do atleta é obrigatório.");
        }

        if (a.getCategoria() == null || a.getCategoria().isEmpty()) {
            throw new Exception("Nome da categoria é obrigatório.");
        }

        repositorio.salvarAtleta(a);
    }

    public void criarInscricao(Competicao c, Atleta a) throws Exception {
        List<Inscricao> inscricoesAtuais = repositorio.listarInscricoes();

        if (!temVaga(c, inscricoesAtuais)) {
            throw new Exception("Competição lotada.");
        }

        if (inscricaoRepetida(c, inscricoesAtuais, a)) {
            throw new Exception("Atleta já inscrito.");
        }

        Inscricao i = new Inscricao(a, c);
        repositorio.salvarInscricao(i);
    }
}
