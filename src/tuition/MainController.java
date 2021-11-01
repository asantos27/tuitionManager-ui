package tuition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    @FXML
    private DatePicker paymentDate;

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
    private Button updateStudyAbroad;

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
     * Helper method to disable nonresident options
     */
    @FXML
    void disableResidencyStatuses(ActionEvent event) {
        if (residentButton.isSelected()) {
            tristateButton.setDisable(true);
            NYButton.setDisable(true);
            CTButton.setDisable(true);
            internationalButton.setDisable(true);
            studyAbroadBox.setDisable(true);
        }

        if (nonResidentButton.isSelected()) {
            tristateButton.setDisable(false);
            NYButton.setDisable(false);
            CTButton.setDisable(false);
            internationalButton.setDisable(false);
            studyAbroadBox.setDisable(true);
        }

        if (tristateButton.isSelected()) {
            internationalButton.setSelected(false);
            studyAbroadBox.setDisable(false);
        }

        if (internationalButton.isSelected()) {
            studyAbroadBox.setDisable(false);
            tristateButton.setSelected(false);
            NYButton.setDisable(true);
            CTButton.setDisable(true);
        }

        if (tristateButton.isSelected()) {
            internationalButton.setSelected(false);
            studyAbroadBox.setDisable(false);
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

    /**
     * Method to give functionality to remove student button
     * @param event
     */
    @FXML
    void removeStudent(ActionEvent event) {
        String[] profile = new String[] {name1.getText(), getMajor()};

        Student student = new Student(profile);
        if (!(roster.isStudentAvailable(student))) {
            messageArea.appendText("Student is not in the roster. \n");
            System.out.println("Student is not in the roster.");
        } else {
            roster.remove(student);
            messageArea.appendText("Student removed from the roster. \n");
        }
    }

    /**
     * Method to calculate tuition for a single student
     * @param event
     */
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

    /**
     * Method to give functionality to making a payment
     * @param event
     */
    @FXML
    void payTuitionHandler(ActionEvent event) {
        if (name2.getText() == null || name2.getText().trim().isEmpty()) {
            messageArea.appendText("Please enter a name.\n");
            return;
        }

        if (paymentAmount.getText() == null || paymentAmount.getText().trim().isEmpty()) {
            messageArea.appendText("Payment amount missing.\n");
            return;
        }

        String[] profile = new String[] {name2.getText(), getMajor()};
        Student student = new Student(profile);
        if (!roster.isStudentAvailable(student)) {
            messageArea.appendText("Student is not in the roster.\n");
            return;
        }

        Student enrolledStudent = roster.getStudent(student);
        enrolledStudent.tuitionDue(); //calculate student's tuition
        enrolledStudent.setLastPaymentAmount(Double.parseDouble(paymentAmount.getText()));

        //check if date is valid
        if (paymentDate.getValue() == null) {
            messageArea.appendText("Payment date is missing.\n");
            return;
        }
        String tempDate = paymentDate.getValue().toString(); //YYYY-MM-DD
        String month = tempDate.substring(5,7);
        String day = tempDate.substring(8);
        String year = tempDate.substring(0,4);

        Date date = new Date(Integer.parseInt(month), Integer.parseInt(day), Integer.parseInt(year));
        if (!date.isValid()) {
            messageArea.appendText("Payment date invalid.\n");
            return;
        } else {
            enrolledStudent.setLastPaymentDate(date);
        }

        Double paymentAmountEntered = Double.parseDouble(paymentAmount.getText());
        if (paymentAmountEntered <= 0) {
            messageArea.appendText("Invalid amount. \n");
            return;
        } else if (paymentAmountEntered > enrolledStudent.getTuition()) {
            messageArea.appendText("Amount is greater than amount due. \n");
            return;
        } else {
            enrolledStudent.setLastPaymentAmount(paymentAmountEntered);
            enrolledStudent.setTuition(enrolledStudent.getTuition() - enrolledStudent.getLastPaymentAmount());
            enrolledStudent.setTotalPayment(enrolledStudent.getLastPaymentAmount());
            messageArea.appendText("Payment applied. \n");
        }
    }

    /**
     * Method to give functionality to entering and updating financial aid
     * @param event
     */
    @FXML
    void setFinancialAidAmtHandler(ActionEvent event) {
        if (name2.getText() == null || name2.getText().trim().isEmpty()) {
            messageArea.appendText("Please enter a name.\n");
            return;
        }

        if (finAidAmt.getText() == null || finAidAmt.getText().trim().isEmpty()) {
            messageArea.appendText("Payment amount missing.\n");
            return;
        }

        String[] profile = new String[] {name2.getText(), getMajor()};
        Student student = new Student(profile);
        if (!roster.isStudentAvailable(student)) {
            messageArea.appendText("Student is not in the roster.\n");
            return;
        }

        Double finAidAmountEntered = Double.parseDouble(finAidAmt.getText());
        if ((finAidAmountEntered < 0 || finAidAmountEntered > 10000)) {
            messageArea.appendText("Invalid amount. \n");
            return;
        }

        Student enrolledStudent = roster.getStudent(student);
        enrolledStudent.tuitionDue();
        if (enrolledStudent.getCreditHours() < 12) {
            messageArea.appendText("Parttime student doesn't qualify for the award. \n");
            return;
        }
        if (!(enrolledStudent instanceof Resident)) {
            messageArea.appendText("Not a resident student. \n");
            return;
        }

        if (enrolledStudent.getFinancialAid() != 0) {
            messageArea.appendText("Awarded once already. \n");
            return;
        }
        enrolledStudent.setFinancialAid(finAidAmountEntered);
        enrolledStudent.setTuition(enrolledStudent.getTuition() - enrolledStudent.getFinancialAid());
        messageArea.appendText("Tuition updated. \n");
    }

    /**
     * Method to update international students' status to study abroad
     * @param event
     */
    @FXML
    void updateStudyAbroadStatus(ActionEvent event) {
        if (name2.getText() == null || name2.getText().trim().isEmpty()) {
            messageArea.appendText("Please enter a name.\n");
            return;
        }

        String[] profile = new String[] {name2.getText(), getMajor()};
        International student = new International(profile);
        if (!roster.isStudentAvailable(student)) {
            messageArea.appendText("Couldn't find the international student. \n");
            return;
        }

        Student enrolledStudent = roster.getStudent(student);
        if (enrolledStudent instanceof International) {
            if (enrolledStudent.getCreditHours() > 12) {
                enrolledStudent.setCreditHours(12);
            }
            ((International) enrolledStudent).setStudyAbroad(true);
            enrolledStudent.setTotalPayment(-enrolledStudent.getTotalPayment());
            enrolledStudent.setLastPaymentAmount(0.0);
            enrolledStudent.setLastPaymentDate(null);
            enrolledStudent.tuitionDue();
            messageArea.appendText("Tuition updated. \n");
        }
    }

    /**
     * Method to add functionality to button that calculates tuition for all students in the roster
     * @param event
     */
    @FXML
    void calculateAllTuitions(ActionEvent event) {
        roster.calculateTuitionOfEveryStudent();
        messageArea.appendText("Calculation completed. \n");
    }

    /**
     * Method to give functionality to printing in third tab
     * @param event
     */
    @FXML
    void print(ActionEvent event) {
        if (currOrder.isSelected()) {
            messageArea.appendText(roster.print() + "\n");
        } else if (studentNames.isSelected()) {
            messageArea.appendText(roster.printByStudentName() + "\n");
        } else if (lastDate.isSelected()) {
            messageArea.appendText(roster.printByPaymentDate() + "\n");
        }
    }

}




