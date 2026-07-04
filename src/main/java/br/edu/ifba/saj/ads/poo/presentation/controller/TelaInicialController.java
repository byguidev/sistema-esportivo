package br.edu.ifba.saj.ads.poo.presentation.controller;

import br.edu.ifba.saj.ads.poo.business.ServicoAtividadesEsportivas;
import br.edu.ifba.saj.ads.poo.data.RepositorioAtividades;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class TelaInicialController {
    RepositorioAtividades repositorio = new RepositorioAtividades();
    ServicoAtividadesEsportivas servico = new ServicoAtividadesEsportivas(repositorio);

    @FXML private Button competicaoBtn;
    @FXML private Button atletaBtn;

    public static void exibirAlerta(Alert.AlertType tipo, String titulo, String cabecalho, String mensagem) {  
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    private void abrirCadastroCompeticao() throws Exception {
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
    }

    @FXML
    private void abrirCadastroAtleta() throws Exception {
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
    }
}
