package tuition;

import java.text.DecimalFormat;

/**
 * This class is specifically used for international students
 * It is a subclass of NonResident and overrides the tuitionDue methods.
 * @author Daniel Flts, Alyssa Santos
 */
public class International extends NonResident {
    private static double additionalFee = 2650;
    private static double internationalStudentTuition = 29737;

    /**
     * Constructor for International class
     * @param String array from user command line
     */
    public International(String[] token) {
        super(token);
    }


    /**
     * Method that will be implemented in Student's subclasses
     * Calculates the tuition due for an international student
     */
    @Override
    public void tuitionDue() {
        if (getStudyAbroad() == true) { //only pay fees
            this.setTuition(getUniversityFee() + additionalFee - getTotalPayment());
        } else {
            this.setTuition(internationalStudentTuition + getUniversityFee() + additionalFee - getTotalPayment());
        }
    }

    /**
     * Overrides Student toString method
     * @return string representation of Student
     */
    @Override
    public String toString() {
        return super.toString();
    }
}