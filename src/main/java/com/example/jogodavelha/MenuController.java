package com.example.jogodavelha;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class MenuController {
    Alert warningAlert = new Alert(AlertType.WARNING);
    Alert informationAlert = new Alert(AlertType.INFORMATION);

    @FXML
    private ChoiceBox<String> choiceBox1;
    @FXML
    private ChoiceBox<String> choiceBox2;
    @FXML
    private TextField textFieldPlayer1;
    @FXML
    private TextField textFieldPlayer2;

    public void onMouseClickedPlay(MouseEvent mouseEvent) {
        String text1 = textFieldPlayer1.getText();
        String text2 = textFieldPlayer2.getText();

        String choice1 = choiceBox1.getValue();
        String choice2 = choiceBox2.getValue();

        if (ValidateName(text1, text2)) {
            warningAlert.setContentText("Precisa informar os nomes dos players e não podem ser iguais!!\n");
            warningAlert.show();
        }else if(ValidateSymbol(choice1, choice2)) {
            warningAlert.setContentText("Precisa escolher um símbolo para jogar!!");
            warningAlert.show();
        }else {
            System.out.println("TextField 1: " + (text1) + (choice1));
            System.out.println("TextField 2: " + (text2) + (choice2));
        }
    }

    // Função que recebe um mouse event de click, ou seja,
    // quando o botão que tem esse evento for clicado é setado
    // a mensagem do alert e depois mostrada para o usuário.
    public void onMouseClickedRules(MouseEvent mousEvent){
        informationAlert.setContentText("""
                - A partida pode ter um vencedor ou terminar empatada.
                - Os jogadores devem se identificar e escolher o símbolo.
                - O primeiro jogador a informar o nome inicia a partida.
                - Ao final da partida existe a possibilidade de revanche.
                - Ao iniciar uma nova partida, alterna-se o jogador que inicia.
                """);
        informationAlert.show();
    }

    // A função valida o nome dos usuários, ou seja,
    // não pode conter apenas espaços (verificado pelo regex),
    // os inputs não podem estar vazios e iguais.
    private boolean ValidateName(String nick1, String nick2) {
        return !nick1.matches("^\\S+$") || !nick2.matches("^\\S+$") || nick1.isEmpty() || nick2.isEmpty() || nick1.equals(nick2);
    }

    // A função verifica se o usuário escolheu algum símbolo,
    // ou seja, não pode ser nulo.
    private boolean ValidateSymbol(String symbol1, String symbol2) {
        return symbol1 == null || symbol2 == null;
    }

    @FXML
    public void initialize() {
        choiceBox1.getItems().addAll("X", "O");
        choiceBox2.getItems().addAll("X", "O");

        choiceBox1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                choiceBox2.getSelectionModel().select(newValue.equals("X") ? "O" : "X");
            }
        });

        choiceBox2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                choiceBox1.getSelectionModel().select(newValue.equals("X") ? "O" : "X");
            }
        });
    }
}
