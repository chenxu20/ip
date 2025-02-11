package mary.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mary.chatbot.Mary;

/**
 * A GUI for Mary using FXML.
 */
public class Main extends Application {

    private Mary mary = new Mary("./Task.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMary(mary);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
