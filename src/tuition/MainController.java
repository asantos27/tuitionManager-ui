package tuition;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * This class --
 * @author Daniel Flts, Alyssa Santos
 */
public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}