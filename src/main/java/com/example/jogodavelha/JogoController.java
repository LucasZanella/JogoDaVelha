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

    // Instância da classe Game, que gerencia o estado geral do jogo.
    private Game game;

    // Cria a instância dos alertas de aviso e de informação.
    Alert warningAlert = new Alert(Alert.AlertType.WARNING);
    Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
    Alert resultAlert = new Alert(Alert.AlertType.INFORMATION);

    // Define campos de interface do usuário vinculados aos elementos definidos no arquivo FXML.
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

    // Atualiza a interface do usuário com as informações do jogo.
    private void updateUI(){

        nickPlayer1.setText(game.getNameP1());                          // Atualiza o campo de texto com o nome do jogador 1.
        choiceSymbolP1.setText(String.valueOf(game.getSymbolP1()));     // Atualiza o símbolo do jogador 1 exibido na interface.
        victoriesP1.setText(String.valueOf(game.getVictoriesP1()));     // Atualiza o número de vitórias do jogador 1.

        nickPlayer2.setText(game.getNameP2());                          // Atualiza o campo de texto com o nome do jogador 2.
        choiceSymbolP2.setText(String.valueOf(game.getSymbolP2()));     // Atualiza o símbolo do jogador 2 exibido na interface.
        victoriesP2.setText(String.valueOf(game.getVictoriesP2()));     // Atualiza o número de vitórias do jogador 2

        draw.setText(String.valueOf(game.getDraw()));                   // Atualiza o número de empates.
    }

    // Abre a tela do menu.
    private void openFirstScreen() {
        try {

            // Responsável por carregar o layout da interface do usuário e o conteúdo a partir de um arquivo FXML.
            FXMLLoader loader = new FXMLLoader(JogoApplication.class.getResource("menu-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();          // Cria uma nova instância de Stage, que é uma janela em JavaFX;
            stage.setScene(new Scene(root));    // Define a cena da nova janela;

            stage.setTitle("Jogo da Velha");    // Define o título da página;
            stage.setResizable(false);          // Define a janela como não redimensionável;
            stage.show();                       // Exibe a nova janela na tela.

        // Mostra o rastreamento dos erros que possam ocorrer durante a execução deste código.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Fecha a janela atual quando um evento de mouse ocorre, no caso, "Voltar".
    private void closeCurrentWindow(MouseEvent event) {

        // Obtém a janela (Stage) atual a partir do evento de mouse.
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.close();      // Fecha a janela atual.
    }

    // Fecha a janela atual quando os players voltam para o menu.
    private void closeCurrentWindow() {

        // Obtém a janela (Stage) atual a partir do evento de mouse.
        Stage stage = (Stage) gridPane.getScene().getWindow();

        stage.close();      // Fecha a janela atual.
    }

    // Mostra um alerta com o resultado e outro com opções para reiniciar o jogo ou voltar ao menu.
    private void showResultAlert(String title, String message) {

        // Alerta que mostra o resultado.
        informationAlert.setTitle("Resultado!");                        // Define o título do alerta.
        informationAlert.setHeaderText("Parabéns!!");                   // Define o cabeçalho do alerta.
        informationAlert.setContentText(message);                       // Define o conteúdo do alerta com a mensagem passada como parâmetro.
        informationAlert.show();                                        // Mostra o alerta e espera pela resposta do usuário.

        // Alerta que trata a escolha de reiniciar o jogo ou voltar para o menu.
        resultAlert.getDialogPane().getButtonTypes().clear();           // Remove os botões padrão do alerta.

        // Adiciona um botão "Voltar ao Menu".
        ButtonType returnToMenuButtonType = new ButtonType("Voltar ao Menu", ButtonBar.ButtonData.CANCEL_CLOSE);
        resultAlert.getButtonTypes().add(returnToMenuButtonType);

        // Adiciona um botão "Reiniciar Jogo".
        ButtonType restartButtonType = new ButtonType("Reiniciar Jogo", ButtonBar.ButtonData.OK_DONE);
        resultAlert.getButtonTypes().add(restartButtonType);

        resultAlert.setHeaderText("Escolha uma opção!");                // Define o cabeçalho do alerta.

        // Mostra o alerta e executa ações com base na escolha do usuário.
        resultAlert.showAndWait().ifPresent(response -> {

            // Se o usuário escolher "Reiniciar Jogo", chama a função para reiniciar o jogo
            if (response == restartButtonType) {
                restartGame();

            // Se o usuário escolher "Voltar ao Menu", fecha a janela atual e abre o menu
            } else if (response == returnToMenuButtonType) {
                closeCurrentWindow();
                openFirstScreen();
            }
        });
    }

    // Limpa o GridPane.
    private void resetGridPane() {

        // Remove todos os filhos do GridPane cujas posições de coluna e linha não são nulas.
        gridPane.getChildren().removeIf(node -> GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null);
    }

    // Reinicia o jogo.
    private void restartGame() {
        resetGridPane();        // Limpa todos os símbolos do GridPane.
        game.reset();           // Reseta o estado do jogo.
        updateUI();             // Atualiza a interface do usuário.
    }

    // Fecha a janela atual quando um evento de mouse ocorre, no caso, "Voltar".
    @FXML
    private void onMouseClickedReturn(MouseEvent mouseEvent) {
        openFirstScreen();                  // Abre a tela inicial do menu.
        closeCurrentWindow(mouseEvent);     // Fecha a janela atual.
    }

    // Configura os eventos no GridPane.
    @FXML
    public void initialize(){

        // Define o evento que ocorre quando o GridPane é clicado.
        gridPane.setOnMouseClicked(event -> {

            // Obtém a posição do clique em relação ao GridPane.
            double x = event.getX();
            double y = event.getY();

            // Converte as coordenadas do clique para índices de célula no GridPane.
            int column = (int) (x / (gridPane.getWidth() / 3));
            int row = (int) (y / (gridPane.getHeight() / 3));

            // Converte a posição 2D (linha e coluna) para um índice 1D.
            int index = row * 3 + column;

            // Verifica se a célula selecionada já foi preenchida.
            if (game.getGameXO()[index] == '\u0000') {

                // Executa a jogada e alterna o turno dos jogadores.
                game.play(index);

                // Cria um Label para mostrar o símbolo do jogador na célula
                // clicada com base na posição index do array gameXO.
                Label label = new Label(String.valueOf(game.getGameXO()[index]));

                // Define o tamanho da fonte do Label.
                label.setStyle("-fx-font-size: 35px;");

                // Centraliza o label no GridPane.
                GridPane.setHalignment(label, HPos.CENTER);
                GridPane.setValignment(label, VPos.CENTER);

                // Adiciona o label no GridPane na posição correta.
                gridPane.add(label, column, row);

                // Verifica se o jogador atual venceu o jogo.
                if (game.checkVictory(game.getGameXO()[index])) {

                    // Mostra um alerta informando quem venceu.
                    showResultAlert("Vitória", game.getPlayer1Turn() ? game.getNameP2() + " venceu!" : game.getNameP1() + " venceu!");

                // Verifica se o jogo terminou em empate.
                } else if (game.checkDraw()) {

                    // Mostra um alerta informando que o jogo empatou.
                    showResultAlert("Empate", "O jogo terminou em empate!");
                }

                // Atualiza a interface do usuário com as novas informações
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