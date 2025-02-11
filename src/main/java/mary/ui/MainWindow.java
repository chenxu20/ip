package mary.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import mary.chatbot.Mary;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Mary mary;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image maryImage = new Image(this.getClass().getResourceAsStream("/images/Mary.png"));

    /**
     * Initialises the GUI
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        DialogBox welcomeMessage = DialogBox.getMaryDialog("Hello! I'm Mary\n"
                + "What can I do for you?\n\n", maryImage);
        dialogContainer.getChildren().addAll(welcomeMessage);
    }

    /** Injects the Mary instance */
    public void setMary(Mary m) {
        mary = m;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Mary's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = mary.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMaryDialog(response, maryImage));
        userInput.clear();
    }
}
