package br.edu.ifba.saj.ads.poo.presentation.controller;

import br.edu.ifba.saj.ads.poo.business.ServicoAtividadesEsportivas;
import br.edu.ifba.saj.ads.poo.model.Atleta;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CadastroAtletaController {
    ServicoAtividadesEsportivas servico;

    @FXML private TextField nome;
    @FXML private TextField categoria;
    @FXML private Button submitBtn;

    public void setServico(ServicoAtividadesEsportivas servico) {
        this.servico = servico;
    }

    public void salvarAtleta() throws Exception {
        Atleta novoAtleta = new Atleta(nome.getText(), categoria.getText());
        servico.criarAtleta(novoAtleta);
    }

    public void onSubmit() {
        try {
            salvarAtleta();
            TelaInicialController.exibirAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Cadastro Realizado", "O atleta foi salvo com sucesso!");
            nome.clear();
            categoria.clear();
        } catch(Exception e) {
            TelaInicialController.exibirAlerta(Alert.AlertType.INFORMATION, "Erro de validação", "Não foi possível salvar", e.getMessage());
        }
    }
}