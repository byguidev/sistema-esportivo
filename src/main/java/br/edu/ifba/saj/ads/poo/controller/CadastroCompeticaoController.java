package br.edu.ifba.saj.ads.poo.controller;

import br.edu.ifba.saj.ads.poo.business.ServicoAtividadesEsportivas;
import br.edu.ifba.saj.ads.poo.model.Competicao;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class CadastroCompeticaoController {
    ServicoAtividadesEsportivas servico;
    private Competicao competicaoEditando;

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

    public void setCompeticaoEditando(Competicao competicao) {
        this.competicaoEditando = competicao;
        if (competicao != null) {
            nome.setText(competicao.getNome());
            data.setValue(competicao.getData());
            limiteParticipantes.getValueFactory().setValue(competicao.getLimite());
            submitBtn.setText("Salvar Alterações");
        }
    }

    public void salvarCompeticao() throws Exception {
        if (competicaoEditando == null) {
            Competicao novaCompeticao = new Competicao(nome.getText(), data.getValue(), limiteParticipantes.getValue());
            servico.criarCompeticao(novaCompeticao);
            return;
        }

        competicaoEditando.setNome(nome.getText());
        competicaoEditando.setData(data.getValue());
        competicaoEditando.setLimite(limiteParticipantes.getValue());
        servico.atualizarCompeticao(competicaoEditando);
    }

    public void onSubmit() {
        try {
            salvarCompeticao();
            MainController.exibirAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Operação Realizada", "A competição foi salva com sucesso!");
            if (competicaoEditando != null) {
                fecharJanela();
            } else {
                nome.clear();
                data.setValue(null);
                limiteParticipantes.getValueFactory().setValue(1);
            }
            competicaoEditando = null;
        } catch(Exception e) {
            MainController.exibirAlerta(Alert.AlertType.ERROR, "Erro de validação", "Não foi possível salvar", e.getMessage());
        }
    }

    private void fecharJanela() {
        ((Node) submitBtn).getScene().getWindow().hide();
    }
}