package com.example.jogodavelha;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class JogoApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // Responsável por carregar o conteúdo a partir de um arquivo FXML.
        FXMLLoader fxmlLoader = new FXMLLoader(JogoApplication.class.getResource("menu-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());         // Carrega o layout da tela a partir do arquivo FXML.
        stage.setTitle("Jogo da Velha");                    // Define o título da janela.
        stage.setScene(scene);                              // Define a cena da janela com o conteúdo carregado.
        stage.setResizable(false);                          // Define a janela como não redimensionável.
        stage.show();                                       // Exibe a janela.
    }

    public static void main(String[] args) {
        launch();
    }
}