package br.edu.ifba.saj.ads.poo.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import br.edu.ifba.saj.ads.poo.model.*;
import br.edu.ifba.saj.ads.poo.business.ServicoAtividadesEsportivas;
import br.edu.ifba.saj.ads.poo.data.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class CadastroResultadoController {
    ServicoAtividadesEsportivas servico;
    RepositorioAtividades repositorio;

    @FXML ComboBox<Competicao> comboBoxCompeticao;
    @FXML ComboBox<Inscricao> primeiroLugar;
    @FXML ComboBox<Inscricao> segundoLugar;
    @FXML ComboBox<Inscricao> terceiroLugar;

    @FXML public void initialize() {
        comboBoxCompeticao.valueProperty().addListener((observable, oldValue, newValue) -> carregarInscricoes(newValue));
    }

    public void setServico(ServicoAtividadesEsportivas servico) {
        this.servico = servico;
    }

    public void carregarDados(RepositorioAtividades repositorio) {
        this.repositorio = repositorio;

        ObservableList<Competicao> obsCompeticao = FXCollections.observableArrayList(repositorio.listarCompeticoes());

        comboBoxCompeticao.setItems(obsCompeticao);

        carregarInscricoes(comboBoxCompeticao.getValue());
    }

    private void carregarInscricoes(Competicao competicao) {
        if (repositorio == null || competicao == null) {
            primeiroLugar.setItems(FXCollections.observableArrayList());
            segundoLugar.setItems(FXCollections.observableArrayList());
            terceiroLugar.setItems(FXCollections.observableArrayList());
            primeiroLugar.setValue(null);
            segundoLugar.setValue(null);
            terceiroLugar.setValue(null);
            return;
        }

        ObservableList<Inscricao> inscricoesDaCompeticao = FXCollections.observableArrayList(
            repositorio.listarInscricoes()
            .stream()
            .filter(i -> i.getCompeticao().getId() == competicao.getId())
            .toList()
        );

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

        Resultado novoResultado = new Resultado(
            comboBoxCompeticao.getValue(), 
            primeiroLugar.getValue().getAtleta(), 
            segundoLugar.getValue().getAtleta(), 
            terceiroLugar.getValue().getAtleta());
        servico.criarResultado(novoResultado);
    }

    @FXML private void onSubmit() {
        try {
            salvarResultado();
            MainController.exibirAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Resultado Realizado", "O resultado foi salvo com sucesso!");
            comboBoxCompeticao.setValue(null);
            primeiroLugar.setValue(null);
            segundoLugar.setValue(null);
            terceiroLugar.setValue(null);
        } catch(Exception e) {
            MainController.exibirAlerta(Alert.AlertType.INFORMATION, "Erro de validação", "Não foi possível salvar", e.getMessage());
        }
    }
}