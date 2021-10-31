package tuition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * This class --
 * @author Daniel Flts, Alyssa Santos
 */
public class MainController {

    @FXML
    private TextField name;

    @FXML
    private RadioButton majorCS;

    @FXML
    private RadioButton majorEE;

    @FXML
    private RadioButton majorME;

    @FXML
    private RadioButton majorIT;

    @FXML
    private RadioButton majorBA;

    @FXML
    private RadioButton resident;

    @FXML
    private RadioButton nonResident;

    @FXML
    private RadioButton tri;

    @FXML
    private RadioButton newYork;

    @FXML
    private RadioButton conn;

    @FXML
    private RadioButton inter;

    @FXML
    private CheckBox studyAbroad;

    @FXML
    private TextField validCredits;

    @FXML
    private Button addStudentButton;

    @FXML
    private Button removeStudentButton;

    @FXML
    private Button tuitionDueButton;

    @FXML
    private TextField tuition;

    @FXML
    private TextArea messageArea;

    @FXML
    private TextField name2;

    @FXML
    private RadioButton majorCS2;

    @FXML
    private RadioButton majorEE2;

    @FXML
    private RadioButton majorME2;

    @FXML
    private RadioButton majorIT2;

    @FXML
    private RadioButton majorBA2;

    @FXML
    private TextField paymentAmount;

    @FXML
    private Button pay;

    @FXML
    private TextField finAidAmt;

    @FXML
    private Button set;

    @FXML
    private RadioButton studyAbroadTrue;

    @FXML
    private Button calculate;

    @FXML
    private RadioButton currOrder;

    @FXML
    private RadioButton studentNames;

    @FXML
    private RadioButton lastDate;

    @FXML
    private Button print;

    Roster roster = new Roster();


}

