package tuition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;

/**
 * This class --
 * @author Daniel Flts, Alyssa Santos
 */
public class MainController {


    @FXML
    private Button addStudentButton;

    @FXML
    private Button calculate;

    @FXML
    private RadioButton CTButton;

    @FXML
    private RadioButton currOrder;

    @FXML
    private TextField finAidAmt;

    @FXML
    private RadioButton internationalButton;

    @FXML
    private RadioButton lastDate;

    @FXML
    private RadioButton majorBA1;

    @FXML
    private RadioButton majorBA2;

    @FXML
    private RadioButton majorCS1;

    @FXML
    private RadioButton majorCS2;

    @FXML
    private RadioButton majorEE1;

    @FXML
    private RadioButton majorEE2;

    @FXML
    private RadioButton majorIT1;

    @FXML
    private RadioButton majorIT2;

    @FXML
    private RadioButton majorME1;

    @FXML
    private RadioButton majorME2;

    @FXML
    private TextArea messageArea;

    @FXML
    private TextField name1;

    @FXML
    private TextField name2;

    @FXML
    private RadioButton NYButton;

    @FXML
    private RadioButton nonResidentButton;

    @FXML
    private Button pay;

    @FXML
    private TextField paymentAmount;

//    @FXML
//    private DatePicker paymentDate;

    @FXML
    private Button print;

    @FXML
    private Button removeStudentButton;

    @FXML
    private RadioButton residentButton;

    @FXML
    private Button set;

    @FXML
    private RadioButton studentNames;

    @FXML
    private CheckBox studyAbroadBox;

    @FXML
    private RadioButton studyAbroadTrue;

    @FXML
    private RadioButton tristateButton;

    @FXML
    private TextField tuition;

    @FXML
    private Button tuitionDueButton;

    @FXML
    private TextField validCredits;

    Roster roster = new Roster();

    /**
     * Helper method to create profile when adding student
     * @return Profile of current student
     */
    @FXML
    private String getMajor() {
        try {
            String major = "";
            if (majorCS1.isSelected()) {
                major = "CS";
            } else if (majorEE1.isSelected()) {
                major = "EE";
            } else if (majorME1.isSelected()) {
                major = "ME";
            } else if (majorIT1.isSelected()) {
                major = "IT";
            } else if (majorBA1.isSelected()) {
                major = "BA";
            }

            return major;

        } catch (NullPointerException e) {
            messageArea.appendText("Not a valid major" + "\n");
            return null;
        }
    }

    /**
     * Method to give functionality to add student button
     * @param event
     */
    @FXML
    void addStudent(ActionEvent event) {
        if (name1.getText() == null || name1.getText().trim().isEmpty()) {
            messageArea.appendText("Please enter a name.\n");
            return;
        }

        int credits = Integer.parseInt(validCredits.getText());
        try {
            if (credits < 0) {
                messageArea.appendText("Credit hours cannot be negative.\n");
                return;
            } else if (credits < 3) {
                messageArea.appendText("Minimum credit hours is 3.\n");
                return;
            } else if (credits > 24) {
                messageArea.appendText("Credit hours exceed the maximum 24.\n");
                return;
            }
        } catch (NumberFormatException e) {
            messageArea.appendText("Invalid credit hours.\n");
            return;
        }

        String[] profile = new String[] {name1.getText(), getMajor()};

        if (residentButton.isSelected()) {
            Resident resident = new Resident(profile);
            resident.setResidencyStatus("resident");
            resident.setCreditHours(credits);

            if (roster.isStudentAvailable(resident)) {
                messageArea.appendText("Student is already in the roster. \n");
                return;
            } else {
                roster.add(resident);
                messageArea.appendText("Student added. \n");
            }

        } else if (nonResidentButton.isSelected() && !(tristateButton.isSelected()) && !(internationalButton.isSelected())) { //nonresident student but not a tristate student
            NonResident nonresident = new NonResident(profile);
            nonresident.setResidencyStatus("nonresident");
            nonresident.setCreditHours(credits);

            if (roster.isStudentAvailable(nonresident)) {
                messageArea.appendText("Student is already in the roster. \n");
                return;
            } else {
                roster.add(nonresident);
                messageArea.appendText("Student added. \n");
            }

        } else if (nonResidentButton.isSelected() && tristateButton.isSelected() && !(internationalButton.isSelected())) { //tristate student
            TriState tristate = new TriState(profile);
            tristate.setResidencyStatus("tristate");
            tristate.setCreditHours(credits);

            if (NYButton.isSelected()) {
                tristate.setState("NY");
            } else if (CTButton.isSelected()) {
                tristate.setState("CT");
            }

            if (roster.isStudentAvailable(tristate)) {
                messageArea.appendText("Student is already in the roster. \n");
                return;
            } else {
                roster.add(tristate);
                messageArea.appendText("Student added. \n");
            }

        } else if (nonResidentButton.isSelected() && !(tristateButton.isSelected()) && internationalButton.isSelected()) {
            International internationalStudent = new International(profile);
            internationalStudent.setResidencyStatus("international");
            internationalStudent.setCreditHours(credits);

            if (studyAbroadBox.isSelected()) {
                internationalStudent.setStudyAbroad(true);
            }

            if (internationalStudent.getCreditHours() < 12) {
                messageArea.appendText("International students must enroll at least 12 credits. \n");
            }

            if (roster.isStudentAvailable(internationalStudent)) {
                messageArea.appendText("Student is already in the roster. \n");
                return;
            } else {
                roster.add(internationalStudent);
                messageArea.appendText("Student added. \n");
            }
        }

    }

    @FXML
    void removeStudent(ActionEvent event) {
    }

    @FXML
    void calculateStudentTuition(ActionEvent event) {
        String[] profile = new String[] {name1.getText(), getMajor()};
        DecimalFormat df = new DecimalFormat("###,##0.00");

        int credits = Integer.parseInt(validCredits.getText());
        try {
            if (credits < 0) {
                messageArea.appendText("Credit hours cannot be negative.\n");
                return;
            } else if (credits < 3) {
                messageArea.appendText("Minimum credit hours is 3.\n");
                return;
            } else if (credits > 24) {
                messageArea.appendText("Credit hours exceed the maximum 24.\n");
                return;
            }
        } catch (NumberFormatException e) {
            messageArea.appendText("Invalid credit hours.\n");
            return;
        }

        if (residentButton.isSelected()) {
            Resident resident = new Resident(profile);
            resident.setCreditHours(credits);
            resident.tuitionDue();
            tuition.setText(String.valueOf(df.format(resident.getTuition())));

        } else if (nonResidentButton.isSelected() && !(tristateButton.isSelected()) && !(internationalButton.isSelected())) {
            NonResident nonResident = new NonResident(profile);
            nonResident.setCreditHours(credits);
            nonResident.tuitionDue();
            tuition.setText(String.valueOf(df.format(nonResident.getTuition())));

        } else if (nonResidentButton.isSelected() && tristateButton.isSelected() && !(internationalButton.isSelected())) {
            TriState triState = new TriState(profile);
            triState.setCreditHours(credits);
            triState.tuitionDue();
            tuition.setText(String.valueOf(df.format(triState.getTuition())));

        } else if (nonResidentButton.isSelected() && !(tristateButton.isSelected()) && internationalButton.isSelected()) {
            International internationalStudent = new International(profile);
            internationalStudent.setCreditHours(credits);

            if (studyAbroadBox.isSelected()) {
                internationalStudent.setStudyAbroad(true);
            }

            internationalStudent.tuitionDue();
            tuition.setText(String.valueOf(df.format(internationalStudent.getTuition())));
        }

    }


    @FXML
    void print(ActionEvent event) {
        //temp just to test
        messageArea.appendText(roster.print()+"\n");
    }

}




