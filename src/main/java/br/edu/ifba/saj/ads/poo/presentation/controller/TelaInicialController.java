package br.edu.ifba.saj.ads.poo.presentation.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TelaInicialController {
    @FXML
    private Button competicaoBtn;

    @FXML
    private void onClick() {
        System.out.println("Botão clicado!");
        competicaoBtn.setText("Clicado");
    }
}
