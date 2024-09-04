package com.example.jogodavelha;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class JogoController {

    @FXML
    private Label nickPlayer1;
    @FXML
    private Label choiceSymbolP1;
    @FXML
    private Label victoriesP1;
    @FXML
    private Label nickPlayer2;
    @FXML
    private Label choiceSymbolP2;
    @FXML
    private Label victoriesP2;
    @FXML
    private Label draw;

    @FXML
    private GridPane gridPane;

    // É passado os inputs para esse controlador e setado a interface
    // com as informações passadas
    public void transferData(Game game){
        nickPlayer1.setText(game.getNameP1());
        choiceSymbolP1.setText(String.valueOf(game.getSymbolP1()));
        victoriesP1.setText(String.valueOf(game.getVictoriesP1()));

        nickPlayer2.setText(game.getNameP2());
        choiceSymbolP2.setText(String.valueOf(game.getSymbolP2()));
        victoriesP2.setText(String.valueOf(game.getVictoriesP2()));

        draw.setText(String.valueOf(game.getDraw()));
    }

    @FXML
    private void onMouseClickedReturn(MouseEvent mouseEvent) {
        openFirstScreen();
        closeCurrentWindow(mouseEvent);
    }

    private void openFirstScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(JogoApplication.class.getResource("menu-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Fecha a janela atual quando um evento de mouse ocorre, no caso, "Voltar".
    private void closeCurrentWindow(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}