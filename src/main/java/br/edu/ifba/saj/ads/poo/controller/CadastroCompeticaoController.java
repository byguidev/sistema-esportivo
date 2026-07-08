package br.edu.ifba.saj.ads.poo.controller;

import br.edu.ifba.saj.ads.poo.business.ServicoAtividadesEsportivas;
import br.edu.ifba.saj.ads.poo.model.Competicao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class CadastroCompeticaoController {
    ServicoAtividadesEsportivas servico;

    @FXML private TextField nome;
    @FXML private DatePicker data;
    @FXML private Spinner<Integer> limiteParticipantes;
    @FXML private Button submitBtn;

    public void initialize() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
        limiteParticipantes.setValueFactory(valueFactory);
    }

    public void setServico(ServicoAtividadesEsportivas servico) {
        this.servico = servico;
    }

    public void salvarCompeticao() throws Exception {
        Competicao novaCompeticao = new Competicao(nome.getText(), data.getValue(), limiteParticipantes.getValue());
        servico.criarCompeticao(novaCompeticao);
    }

    public void onSubmit() {
        try {
            salvarCompeticao();
            MainController.exibirAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Cadastro Realizado", "A competição foi salva com sucesso!");
            nome.clear();
            data.setValue(null);
        } catch(Exception e) {
            MainController.exibirAlerta(Alert.AlertType.INFORMATION, "Erro de validação", "Não foi possível salvar", e.getMessage());
        }
    }
}