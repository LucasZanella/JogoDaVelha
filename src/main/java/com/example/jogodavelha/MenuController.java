package com.example.jogodavelha;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    // Cria a instância dos alertas de aviso e de informação.
    Alert warningAlert = new Alert(AlertType.WARNING);
    Alert informationAlert = new Alert(AlertType.INFORMATION);

    // Define campos de interface do usuário vinculados aos elementos definidos no arquivo FXML.
    @FXML
    private ChoiceBox<Character> choiceBox1;
    @FXML
    private ChoiceBox<Character> choiceBox2;
    @FXML
    private TextField textFieldPlayer1;
    @FXML
    private TextField textFieldPlayer2;

    // Função que recebe um mouse event de click, ou seja,
    // quando o botão (Jogar) for clicado a função é chamada.
    public void onMouseClickedPlay(MouseEvent mouseEvent) {
        // Captura os inputs (nomes) dos textfields.
        String player1Name = textFieldPlayer1.getText();
        String player2Name = textFieldPlayer2.getText();

        // Captura os inputs (Símbolos) das choices boxes.
        Character choice1 = choiceBox1.getValue();
        Character choice2 = choiceBox2.getValue();

        // Os nomes são passados como parâmetros para uma função para serem válidados.
        // Caso não estejam em ordem, uma menssagem é setada no alerta e é mostrado na tela.
        if (ValidateName(player1Name, player2Name)) {
            warningAlert.setHeaderText("Atenção!");
            warningAlert.setContentText("Precisa informar os nomes dos players e não podem ser iguais!!\n");
            warningAlert.show();

        // Os símbolos são passados como parâmetros para outra função para serem validados.
        // Caso não estejam em ordem, uma menssagem é setada no alerta e é mostrado na tela.
        }else if(ValidateSymbol(choice1, choice2)) {
            warningAlert.setHeaderText("Atenção!");
            warningAlert.setContentText("Precisa escolher um símbolo para jogar!!");
            warningAlert.show();

        // Os dados são passados como parâmetros do construtor da classe Game que é instanciada
        // que é instanciada, em seguida, é passada para a função OpenGame e por fim,
        // a função que fecha a tela do menu é chamada.
        }else {
            Game game = new Game(player1Name, player2Name, choice1, choice2, 0, 0, 0);
            OpenGame(game);
            closeCurrentWindow(mouseEvent);
        }
    }

    // Função que recebe um mouse event de click, ou seja,
    // quando o botão (Regras) for clicado a função é chamada.
    public void onMouseClickedRules(){

        // É setado a menssagem do alert e depois mostrado para o usuário.
        informationAlert.setHeaderText("Funcionalidade do jogo");
        informationAlert.setContentText("""
                - A partida pode ter um vencedor ou terminar empatada.
                - Os jogadores devem se identificar e escolher o símbolo.
                - O primeiro jogador a informar o nome inicia a partida.
                - Ao final da partida existe a possibilidade de revanche.
                - Ao iniciar uma nova partida, alterna-se o jogador que inicia.
                """);
        informationAlert.show();
    }

    // A função retorna a validação dos nomes dos usuários.
    private boolean ValidateName(String nick1, String nick2) {

        // O regex verifica se os inputs são somente espaços;
        // Depois é verificado se não foi informado os nomes (.isEmpty());
        // Depois se os nomes são iguais (.equals()).
        return !nick1.matches("^\\S+$") || !nick2.matches("^\\S+$") || nick1.isEmpty() || nick2.isEmpty() || nick1.equals(nick2);
    }

    // A função retorna a validação dos inputs das choices boxes.
    private boolean ValidateSymbol(Character symbol1, Character symbol2) {

        // Só verificado se não foi escolhido os símbolos
        return symbol1 == null || symbol2 == null;
    }

    // A função que inicia o jogo.
    private void OpenGame(Game game){
        try {

            // Responsável por carregar o layout da interface do usuário e o conteúdo a partir de um arquivo FXML.
            FXMLLoader loader = new FXMLLoader(JogoApplication.class.getResource("jogo-view.fxml"));
            Parent root = loader.load();

            // Obtém o controlador associado ao FXML carregado.
            JogoController gameController = loader.getController();

            // Chama uma função do controlador que é usado para passar os dados para a próxima tela.
            gameController.setGame(game);

            // Cria uma nova instância de Stage, que é uma janela em JavaFX;
            // Define a cena da nova janela;
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Define o título da página;
            // Define a janela como não redimensionável;
            // Exibe a nova janela na tela.
            stage.setTitle("Jogo da Velha");
            stage.setResizable(false);
            stage.show();

        // Mostra o rastreamento dos erros que possam ocorrer durante a execução deste código.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // A função que fecha o menu quando o botão "Jogar" clicado.
    private void closeCurrentWindow(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    // Configura as duas ChoiceBox.
    @FXML
    public void initialize() {

        // Adiciona os itens 'X' e 'O' à lista de opções das choices boxes.
        choiceBox1.getItems().addAll('X', 'O');
        choiceBox2.getItems().addAll('X', 'O');

        // Adiciona um ouvinte de mudanças ao modelo de seleção da choice box,
        // esse ouvinte é notificado sempre que a seleção muda.
        choiceBox1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            // Se a nova seleção da choiceBox1 não for nula, é defino o valor
            // da choiceBox2 para o valor oposto do selecionado na choiceBox1.
            if (newValue != null) {
                choiceBox2.setValue(newValue == 'X' ? 'O' : 'X');
            }
        });

        // Adiciona um ouvinte de mudanças ao modelo de seleção da choice box,
        // esse ouvinte é notificado sempre que a seleção muda.
        choiceBox2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            // Se a nova seleção da choiceBox2 não for nula, é defino o valor
            // da choiceBox1 para o valor oposto do selecionado na choiceBox2.
            if (newValue != null) {
                choiceBox1.setValue(newValue == 'X' ? 'O' : 'X');
            }
        });
    }
}