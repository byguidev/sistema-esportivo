package br.edu.ifba.saj.ads.poo.controller;

import br.edu.ifba.saj.ads.poo.business.ServicoAtividadesEsportivas;
import br.edu.ifba.saj.ads.poo.data.RepositorioAtividades;
import br.edu.ifba.saj.ads.poo.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;

public class MainController {
    RepositorioAtividades repositorio = new RepositorioAtividades();
    ServicoAtividadesEsportivas servico = new ServicoAtividadesEsportivas(repositorio);

    @FXML private TableView<Atleta> tabelaAtleta;
    @FXML private TableColumn<Atleta, String> colAtletaNome;
    @FXML private TableColumn<Atleta, String> colAtletaCategoria;

    @FXML private TableView<Competicao> tabelaCompeticao;
    @FXML private TableColumn<Competicao, String> colCompeticaoNome;
    @FXML private TableColumn<Competicao, LocalDate> colCompeticaoData;
    @FXML private TableColumn<Competicao, Integer> colCompeticaoLimite;

    @FXML private Button competicaoBtn;
    @FXML private Button atletaBtn;

    public static void exibirAlerta(Alert.AlertType tipo, String titulo, String cabecalho, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML public void initialize() {
        colAtletaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colAtletaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

        colCompeticaoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCompeticaoData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colCompeticaoLimite.setCellValueFactory(new PropertyValueFactory<>("limite"));

        atualizarTabela();
    }

    private void atualizarTabela() {
        ObservableList<Atleta> listaAtletas = FXCollections.observableArrayList(repositorio.listarAtletas());
        ObservableList<Competicao> listaCompeticoes = FXCollections.observableArrayList(repositorio.listarCompeticoes());

        tabelaAtleta.setItems(listaAtletas);
        tabelaCompeticao.setItems(listaCompeticoes);
    }

    @FXML private void abrirCadastroCompeticao() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/ifba/saj/ads/poo/presentation/view/CadastroCompeticao.fxml"));
        Parent root = loader.load();

        CadastroCompeticaoController controller = loader.getController();
        controller.setServico(this.servico);

        Stage novoStage = new Stage();
        novoStage.setTitle("Cadastro de Competição");
        novoStage.initModality(Modality.APPLICATION_MODAL);
        novoStage.setScene(new Scene(root));
        novoStage.setResizable(false);
        novoStage.showAndWait();

        atualizarTabela();
    }

    @FXML private void abrirCadastroAtleta() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/ifba/saj/ads/poo/presentation/view/CadastroAtleta.fxml"));
        Parent root = loader.load();

        CadastroAtletaController controller = loader.getController();
        controller.setServico(this.servico);

        Stage novoStage = new Stage();
        novoStage.setTitle("Cadastro de Atleta");
        novoStage.initModality(Modality.APPLICATION_MODAL);
        novoStage.setScene(new Scene(root));
        novoStage.setResizable(false);
        novoStage.showAndWait();

        atualizarTabela();
    }
}