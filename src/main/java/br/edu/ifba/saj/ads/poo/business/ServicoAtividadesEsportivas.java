package br.edu.ifba.saj.ads.poo.business;

import br.edu.ifba.saj.ads.poo.data.RepositorioAtividades;
import br.edu.ifba.saj.ads.poo.model.*;
import java.util.List;

public class ServicoAtividadesEsportivas {
    public RepositorioAtividades repositorio;

    public ServicoAtividadesEsportivas(RepositorioAtividades repositorio) {
        this.repositorio = repositorio;
    }

    public boolean validarLimiteParticipantes(Competicao c) {
        long idCompeticao = c.getId();
        int limiteCompeticao = c.getLimite();
        long totalParticipantes = repositorio.listarInscricoes().stream().filter(ins -> ins.getCompeticao().getId() == idCompeticao).count();

        return totalParticipantes < limiteCompeticao;
    }

    public boolean validarInscricaoUnica(Competicao c, Atleta a) {
        long idCompeticao = c.getId();
        List<Inscricao> inscricoesC = repositorio.listarInscricoes().stream().filter(ins -> ins.getCompeticao().getId() == idCompeticao).toList();
        long incricoesRepetidas = inscricoesC.stream().filter(ins -> ins.getAtleta().getId() == a.getId()).count();

        return incricoesRepetidas > 0;
    }

    public boolean validarResultadoUnico(Resultado r) {
        long resultadosRepetidos = repositorio.listarResultados().stream().filter(result -> result.getCompeticao().getId() == r.getCompeticao().getId()).count();

        return resultadosRepetidos > 0;
    }

    public void criarCompeticao(Competicao competicao) throws Exception {
        if (competicao.getNome() == null || competicao.getNome().isEmpty()) {
            throw new Exception("Nome da competição é obrigatório.");
        }

        if (competicao.getLimite() <= 0) {
            throw new Exception("Limite de participantes deve ser maior que 0.");
        }

        repositorio.salvarCompeticao(competicao);
    }

    public void criarAtleta(Atleta atleta) throws Exception {
        if (atleta.getNome() == null || atleta.getNome().isEmpty()) {
            throw new Exception("Nome do atleta é obrigatório.");
        }

        if (atleta.getCategoria() == null || atleta.getCategoria().isEmpty()) {
            throw new Exception("Nome da categoria é obrigatório.");
        }

        repositorio.salvarAtleta(atleta);
    }

    public void criarInscricao(Inscricao inscricao) throws Exception {
        if (inscricao.getAtleta() == null || inscricao.getCompeticao() == null) {
            throw new Exception("Sem dados válidos para inscrição.");
        }

        if (!validarLimiteParticipantes(inscricao.getCompeticao())) {
            throw new Exception("Competição lotada.");
        }

        if (validarInscricaoUnica(inscricao.getCompeticao(), inscricao.getAtleta())) {
            throw new Exception("Atleta já inscrito.");
        }

        repositorio.salvarInscricao(inscricao);
    }

    public void criarResultado(Resultado resultado) throws Exception {
        if (resultado.getCompeticao() == null) {
            throw new Exception("É obrigatório selecionar uma competição.");
        }
        
        if (validarResultadoUnico(resultado)) {
            throw new Exception("Já existe um resultado cadastrado para essa competição.");
        }

        repositorio.salvarResultado(resultado);
    }
}
