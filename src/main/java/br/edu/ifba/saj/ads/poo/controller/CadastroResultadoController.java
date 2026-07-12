package br.edu.ifba.saj.ads.poo.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import br.edu.ifba.saj.ads.poo.model.*;
import br.edu.ifba.saj.ads.poo.business.ServicoAtividadesEsportivas;
import javafx.scene.Node;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class CadastroResultadoController {
    ServicoAtividadesEsportivas servico;
    private Resultado resultadoEditando;

    @FXML ComboBox<Competicao> comboBoxCompeticao;
    @FXML ComboBox<Inscricao> primeiroLugar;
    @FXML ComboBox<Inscricao> segundoLugar;
    @FXML ComboBox<Inscricao> terceiroLugar;

    @FXML public void initialize() {
        comboBoxCompeticao.valueProperty().addListener((observable, oldValue, newValue) -> carregarInscricoes(newValue));
    }

    public void setServico(ServicoAtividadesEsportivas servico) {
        this.servico = servico;
        carregarDados();
    }

    public void setResultadoEditando(Resultado resultado) {
        this.resultadoEditando = resultado;
        if (resultado != null) {
            comboBoxCompeticao.setValue(resultado.getCompeticao());
            carregarInscricoes(resultado.getCompeticao());
            primeiroLugar.setValue(servico.listarInscricoesDaCompeticao(resultado.getCompeticao()).stream().filter(i -> i.getAtleta().getId() == resultado.getPrimeiroLugar().getId()).findFirst().orElse(null));
            segundoLugar.setValue(servico.listarInscricoesDaCompeticao(resultado.getCompeticao()).stream().filter(i -> i.getAtleta().getId() == resultado.getSegundoLugar().getId()).findFirst().orElse(null));
            terceiroLugar.setValue(servico.listarInscricoesDaCompeticao(resultado.getCompeticao()).stream().filter(i -> i.getAtleta().getId() == resultado.getTerceiroLugar().getId()).findFirst().orElse(null));
        }
    }

    public void carregarDados() {
        if (servico == null) {
            return;
        }

        ObservableList<Competicao> obsCompeticao = FXCollections.observableArrayList(servico.listarCompeticoes());

        comboBoxCompeticao.setItems(obsCompeticao);

        carregarInscricoes(comboBoxCompeticao.getValue());
    }

    private void carregarInscricoes(Competicao competicao) {
        if (servico == null || competicao == null) {
            primeiroLugar.setItems(FXCollections.observableArrayList());
            segundoLugar.setItems(FXCollections.observableArrayList());
            terceiroLugar.setItems(FXCollections.observableArrayList());
            primeiroLugar.setValue(null);
            segundoLugar.setValue(null);
            terceiroLugar.setValue(null);
            return;
        }

        ObservableList<Inscricao> inscricoesDaCompeticao = FXCollections.observableArrayList(servico.listarInscricoesDaCompeticao(competicao));

        primeiroLugar.setItems(inscricoesDaCompeticao);
        segundoLugar.setItems(inscricoesDaCompeticao);
        terceiroLugar.setItems(inscricoesDaCompeticao);

        primeiroLugar.setValue(null);
        segundoLugar.setValue(null);
        terceiroLugar.setValue(null);
    }

    private void salvarResultado() throws Exception {
        if (comboBoxCompeticao.getValue() == null || primeiroLugar.getValue() == null || segundoLugar.getValue() == null || terceiroLugar.getValue() == null) {
            throw new Exception("Selecione a competição e os três colocados.");
        }

        long primeiroId = primeiroLugar.getValue().getAtleta().getId();
        long segundoId = segundoLugar.getValue().getAtleta().getId();
        long terceiroId = terceiroLugar.getValue().getAtleta().getId();

        if (primeiroId == segundoId || primeiroId == terceiroId || segundoId == terceiroId) {
            throw new Exception("Os três colocados precisam ser atletas diferentes.");
        }

        if (resultadoEditando == null) {
            Resultado novoResultado = new Resultado(
                comboBoxCompeticao.getValue(), 
                primeiroLugar.getValue().getAtleta(), 
                segundoLugar.getValue().getAtleta(), 
                terceiroLugar.getValue().getAtleta());
            servico.criarResultado(novoResultado);
            return;
        }

        resultadoEditando.setCompeticao(comboBoxCompeticao.getValue());
        resultadoEditando.setPrimeiroLugar(primeiroLugar.getValue().getAtleta());
        resultadoEditando.setSegundoLugar(segundoLugar.getValue().getAtleta());
        resultadoEditando.setTerceiroLugar(terceiroLugar.getValue().getAtleta());
        servico.atualizarResultado(resultadoEditando);
    }

    @FXML private void onSubmit() {
        try {
            salvarResultado();
            MainController.exibirAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Resultado Realizado", "O resultado foi salvo com sucesso!");
            if (resultadoEditando != null) {
                fecharJanela();
            } else {
                comboBoxCompeticao.setValue(null);
                primeiroLugar.setValue(null);
                segundoLugar.setValue(null);
                terceiroLugar.setValue(null);
            }
            resultadoEditando = null;
        } catch(Exception e) {
            MainController.exibirAlerta(Alert.AlertType.ERROR, "Erro de validação", "Não foi possível salvar", e.getMessage());
        }
    }

    private void fecharJanela() {
        ((Node) comboBoxCompeticao).getScene().getWindow().hide();
    }
}