package br.edu.ifba.saj.ads.poo.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import br.edu.ifba.saj.ads.poo.model.*;
import br.edu.ifba.saj.ads.poo.business.ServicoAtividadesEsportivas;
import br.edu.ifba.saj.ads.poo.data.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class CadastroInscricaoController {
    ServicoAtividadesEsportivas servico;

    @FXML ComboBox<Atleta> comboBoxAtleta;
    @FXML ComboBox<Competicao> comboBoxCompeticao;

    public void setServico(ServicoAtividadesEsportivas servico) {
        this.servico = servico;
    }

    public void carregarDados(RepositorioAtividades repositorio) {
        ObservableList<Atleta> obsAtleta = FXCollections.observableArrayList(repositorio.listarAtletas());
        ObservableList<Competicao> obsCompeticao = FXCollections.observableArrayList(repositorio.listarCompeticoes());

        comboBoxAtleta.setItems(obsAtleta);
        comboBoxCompeticao.setItems(obsCompeticao);
    }

    private void salvarInscricao() throws Exception {
        Inscricao novaInscricao = new Inscricao(comboBoxAtleta.getValue(), comboBoxCompeticao.getValue());
        servico.criarInscricao(novaInscricao);
    }

    @FXML private void onSubmit() {
        try {
            salvarInscricao();
            MainController.exibirAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Inscrição Realizada", "A inscrição foi salva com sucesso!");
            comboBoxAtleta.setValue(null);
            comboBoxCompeticao.setValue(null);
        } catch(Exception e) {
            MainController.exibirAlerta(Alert.AlertType.INFORMATION, "Erro de validação", "Não foi possível salvar", e.getMessage());
        }
    }
}