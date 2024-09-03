package com.example.jogodavelha;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class JogoController {
    @FXML
    private Label welcomeText;

    @FXML
    private GridPane gridPane;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}