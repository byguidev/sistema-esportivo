package br.edu.ifba.saj.ads.poo.business;

import br.edu.ifba.saj.ads.poo.data.RepositorioAtividades;
import br.edu.ifba.saj.ads.poo.model.*;
import java.util.List;

public class ServicoAtividadesEsportivas {
    public RepositorioAtividades repositorio;

    public ServicoAtividadesEsportivas(RepositorioAtividades repositorio) {
        this.repositorio = repositorio;
    }

    public List<Competicao> listarCompeticoes() {
        return repositorio.listarCompeticoes();
    }

    public List<Atleta> listarAtletas() {
        return repositorio.listarAtletas();
    }

    public List<Inscricao> listarInscricoes() {
        return repositorio.listarInscricoes();
    }

    public List<Resultado> listarResultados() {
        return repositorio.listarResultados();
    }

    public List<Inscricao> listarInscricoesDaCompeticao(Competicao competicao) {
        if (competicao == null) {
            return List.of();
        }

        long idCompeticao = competicao.getId();
        return repositorio.listarInscricoes().stream()
            .filter(inscricao -> inscricao.getCompeticao().getId() == idCompeticao)
            .toList();
    }

    public void atualizarCompeticao(Competicao competicao) throws Exception {
        if (competicao == null || !repositorio.atualizarCompeticao(competicao)) {
            throw new Exception("Competição não encontrada para atualização.");
        }
    }

    public void removerCompeticao(long id) throws Exception {
        if (!repositorio.removerCompeticao(id)) {
            throw new Exception("Competição não encontrada para remoção.");
        }
    }

    public void atualizarAtleta(Atleta atleta) throws Exception {
        if (atleta == null || !repositorio.atualizarAtleta(atleta)) {
            throw new Exception("Atleta não encontrado para atualização.");
        }
    }

    public void removerAtleta(long id) throws Exception {
        if (!repositorio.removerAtleta(id)) {
            throw new Exception("Atleta não encontrado para remoção.");
        }
    }

    public void atualizarInscricao(Inscricao inscricao) throws Exception {
        if (inscricao == null || !repositorio.atualizarInscricao(inscricao)) {
            throw new Exception("Inscrição não encontrada para atualização.");
        }
    }

    public void removerInscricao(long id) throws Exception {
        if (!repositorio.removerInscricao(id)) {
            throw new Exception("Inscrição não encontrada para remoção.");
        }
    }

    public void atualizarResultado(Resultado resultado) throws Exception {
        if (resultado == null || !repositorio.atualizarResultado(resultado)) {
            throw new Exception("Resultado não encontrado para atualização.");
        }
    }

    public void removerResultado(long id) throws Exception {
        if (!repositorio.removerResultado(id)) {
            throw new Exception("Resultado não encontrado para remoção.");
        }
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
        if (competicao == null) {
            throw new Exception("Dados da competição são obrigatórios.");
        }

        if (competicao.getNome() == null || competicao.getNome().trim().isEmpty()) {
            throw new Exception("Nome da competição é obrigatório.");
        }

        if (competicao.getData() == null) {
            throw new Exception("Data da competição é obrigatória.");
        }

        if (competicao.getLimite() <= 0) {
            throw new Exception("Limite de participantes deve ser maior que 0.");
        }

        repositorio.salvarCompeticao(competicao);
    }

    public void criarAtleta(Atleta atleta) throws Exception {
        if (atleta == null) {
            throw new Exception("Dados do atleta são obrigatórios.");
        }

        if (atleta.getNome() == null || atleta.getNome().trim().isEmpty()) {
            throw new Exception("Nome do atleta é obrigatório.");
        }

        if (atleta.getCategoria() == null || atleta.getCategoria().trim().isEmpty()) {
            throw new Exception("Categoria do atleta é obrigatória.");
        }

        repositorio.salvarAtleta(atleta);
    }

    public void criarInscricao(Inscricao inscricao) throws Exception {
        if (inscricao == null || inscricao.getAtleta() == null || inscricao.getCompeticao() == null) {
            throw new Exception("Sem dados válidos para inscrição.");
        }

        if (!validarLimiteParticipantes(inscricao.getCompeticao())) {
            throw new Exception("Competição completa. Não é possível realizar novas inscrições.");
        }

        if (validarInscricaoUnica(inscricao.getCompeticao(), inscricao.getAtleta())) {
            throw new Exception("Atleta já inscrito nesta competição.");
        }

        repositorio.salvarInscricao(inscricao);
    }

    public void criarResultado(Resultado resultado) throws Exception {
        if (resultado == null || resultado.getCompeticao() == null) {
            throw new Exception("É obrigatório selecionar uma competição.");
        }
        
        if (validarResultadoUnico(resultado)) {
            throw new Exception("Já existe um resultado cadastrado para essa competição.");
        }

        repositorio.salvarResultado(resultado);
    }
}
