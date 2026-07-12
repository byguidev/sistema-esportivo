package br.edu.ifba.saj.ads.poo.business;

import br.edu.ifba.saj.ads.poo.data.RepositorioAtividades;
import br.edu.ifba.saj.ads.poo.model.Atleta;
import br.edu.ifba.saj.ads.poo.model.Competicao;
import br.edu.ifba.saj.ads.poo.model.Inscricao;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ServicoAtividadesEsportivasTest {

    private RepositorioAtividades repositorio;
    private ServicoAtividadesEsportivas servico;

    @BeforeEach
    void setUp() {
        repositorio = new RepositorioAtividades();
        servico = new ServicoAtividadesEsportivas(repositorio);
    }

    @Test
    void devePermitirInscricaoQuandoHaVagaEAtletaAindaNaoInscrito() throws Exception {
        Competicao competicao = new Competicao("Corrida", LocalDate.now().plusDays(10), 2);
        Atleta atleta = new Atleta("Ana", "Adulto");

        servico.criarCompeticao(competicao);
        servico.criarAtleta(atleta);

        servico.criarInscricao(new Inscricao(atleta, competicao));

        assertEquals(1, repositorio.listarInscricoes().size());
        assertEquals(atleta.getId(), repositorio.listarInscricoes().get(0).getAtleta().getId());
    }

    @Test
    void deveFalharAoInscreverQuandoCompeticaoEstiverCompleta() throws Exception {
        Competicao competicao = new Competicao("Nado", LocalDate.now().plusDays(10), 1);
        Atleta primeiroAtleta = new Atleta("Bruno", "Adulto");
        Atleta segundoAtleta = new Atleta("Carla", "Adulto");

        servico.criarCompeticao(competicao);
        servico.criarAtleta(primeiroAtleta);
        servico.criarAtleta(segundoAtleta);

        servico.criarInscricao(new Inscricao(primeiroAtleta, competicao));

        Exception exception = assertThrows(Exception.class, () -> servico.criarInscricao(new Inscricao(segundoAtleta, competicao)));

        assertTrue(exception.getMessage().contains("Competição completa"));
        assertEquals(1, repositorio.listarInscricoes().size());
    }

    @Test
    void deveFalharAoInscreverMesmoAtletaDuasVezesNaMesmaCompeticao() throws Exception {
        Competicao competicao = new Competicao("Judo", LocalDate.now().plusDays(10), 2);
        Atleta atleta = new Atleta("Daniel", "Adulto");

        servico.criarCompeticao(competicao);
        servico.criarAtleta(atleta);

        servico.criarInscricao(new Inscricao(atleta, competicao));

        Exception exception = assertThrows(Exception.class, () -> servico.criarInscricao(new Inscricao(atleta, competicao)));

        assertTrue(exception.getMessage().contains("já inscrito"));
        assertEquals(1, repositorio.listarInscricoes().size());
    }
}