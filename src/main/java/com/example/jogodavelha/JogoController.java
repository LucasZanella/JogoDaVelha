package com.example.jogodavelha;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;

public class JogoController {

    private Game game;

    Alert warningAlert = new Alert(Alert.AlertType.WARNING);
    Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);

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

    // É passado os inputs para esse controlador e
    // setado a interface com as informações passadas.
    public void setGame(Game game){
        this.game = game;
        updateUI();
    }

    // Atualiza a interface.
    private void updateUI(){
        nickPlayer1.setText(game.getNameP1());
        choiceSymbolP1.setText(String.valueOf(game.getSymbolP1()));
        victoriesP1.setText(String.valueOf(game.getVictoriesP1()));

        nickPlayer2.setText(game.getNameP2());
        choiceSymbolP2.setText(String.valueOf(game.getSymbolP2()));
        victoriesP2.setText(String.valueOf(game.getVictoriesP2()));

        draw.setText(String.valueOf(game.getDraw()));
    }

    // Abre o menu.
    private void openFirstScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(JogoApplication.class.getResource("menu-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Jogo da Velha");
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

    // Fecha a janela atual quando os players voltam para o menu.
    private void closeCurrentWindow() {
        Stage stage = (Stage) gridPane.getScene().getWindow();
        stage.close();
    }

    // Mostra o resultado em um alerta e outro alerta pede se os usuários querem revanhe.
    private void showResultAlert(String title, String message) {
        Alert resultAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle("Resultado!");
        informationAlert.setHeaderText(null);
        informationAlert.setContentText(message);
        informationAlert.showAndWait();

        // Remove os botões padrão do alerta
        resultAlert.getDialogPane().getButtonTypes().clear();

        ButtonType returnToMenuButtonType = new ButtonType("Voltar ao Menu", ButtonBar.ButtonData.CANCEL_CLOSE);
        resultAlert.getButtonTypes().add(returnToMenuButtonType);

        ButtonType restartButtonType = new ButtonType("Reiniciar Jogo", ButtonBar.ButtonData.OK_DONE);
        resultAlert.getButtonTypes().add(restartButtonType);

        resultAlert.setHeaderText("Escolha uma opção!");

        resultAlert.showAndWait().ifPresent(response -> {
            if (response == restartButtonType) {
                restartGame();
            } else if (response == returnToMenuButtonType) {
                closeCurrentWindow();
                openFirstScreen();
            }
        });
    }

    // Remove os símbolos do GridPane.
    private void resetGridPane() {
        gridPane.getChildren().removeIf(node -> GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null);
    }

    // Reinicia o jogo.
    private void restartGame() {
        resetGridPane();
        game.reset();
        updateUI();
    }

    // Fecha a janela atual quando um evento de mouse ocorre, no caso, "Voltar".
    @FXML
    private void onMouseClickedReturn(MouseEvent mouseEvent) {
        openFirstScreen();
        closeCurrentWindow(mouseEvent);
    }

    // Configura os eventos no GridPane.
    @FXML
    public void initialize(){

        // Evento de click no GridPane
        gridPane.setOnMouseClicked(event -> {
            // Obtém a posição do clique em relação ao GridPane.
            double x = event.getX();
            double y = event.getY();

            // Converte as coordenadas para índices de célula.
            int column = (int) (x / (gridPane.getWidth() / 3));
            int row = (int) (y / (gridPane.getHeight() / 3));

            // Converte a posição 2D para um índice 1D do array.
            int index = row * 3 + column;

            // Verifica se a célula selecionada já foi preenchida.
            if (game.getGameXO()[index] == '\u0000') {

                // Executa a jogada e alterna o turno.
                game.play(index);

                // Cria um Label com o símbolo que está na posição index do array gameXO.
                Label label = new Label(String.valueOf(game.getGameXO()[index]));

                // Muda o tamanho da fonte do label.
                label.setStyle("-fx-font-size: 35px;");

                // Centraliza o label no GridPane.
                GridPane.setHalignment(label, HPos.CENTER);
                GridPane.setValignment(label, VPos.CENTER);

                // Adiciona o label no GridPane na posição correta.
                gridPane.add(label, column, row);

                if (game.checkVictory(game.getGameXO()[index])) {
                    showResultAlert("Vitória", game.getPlayer1Turn() ? game.getNameP2() + " venceu!" : game.getNameP1() + " venceu!");
                } else if (game.checkDraw()) {
                    showResultAlert("Empate", "O jogo terminou em empate!");
                }

                updateUI();

            } else {
                // Se a célula já estiver preenchida, mostra um alerta.
                warningAlert.setHeaderText("Célula já ocupada");
                warningAlert.setContentText("Escolha outra célula.");
                warningAlert.show();
            }
        });

    }
}