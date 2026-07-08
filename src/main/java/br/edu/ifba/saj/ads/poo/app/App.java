package br.edu.ifba.saj.ads.poo.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/br/edu/ifba/saj/ads/poo/presentation/view/Main.fxml")
        );

        Scene scene = new Scene(loader.load());

        stage.setTitle("Sistema Esportivo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}