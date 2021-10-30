package tuition;

import java.text.DecimalFormat;

/**
 * This class is specifically used for students who do not reside in NJ - specifically the Tristate area.
 * It is a subclass of NonResident and overrides the tuitionDue methods.
 * @author Daniel Flts, Alyssa Santos
 */
public class TriState extends NonResident {

    /**
     * Constructor for TriState class
     * @param String array from user command line
     */
    public TriState(String[] token) {
        super(token);
    }

    /**
     * Method that wil be implemented in Student's subclasses.
     * Calculates the tuition due for a student who is not an NJ resident
     */
    @Override
    public void tuitionDue() {
        if ((getCreditHours() >= 12) && (getCreditHours() <= 16)) { //full time student
            this.setTuition(fullTimeNonResidentTuition + getUniversityFee() - getTuitionDiscount() - getTotalPayment());
        } else if ((getCreditHours() > 16)) { //exceeds 16 credits
            this.setTuition(fullTimeNonResidentTuition + getUniversityFee() + (partTimeNonResidentTuition * (getCreditHours() - 16)) - getTuitionDiscount() - getTotalPayment());
        } else if (getCreditHours() < 12) { //parttime student
            this.setTuition((partTimeNonResidentTuition * getCreditHours()) + (getUniversityFee() * 0.8) - getTotalPayment());
        }
    }

    /**
     * Method that calculates tuition discount based on student's state
     */
    private double getTuitionDiscount() {
        switch (getState().toUpperCase()) {
            case "NY":
                return 4000;
            case "CT":
                return 5000;
            default:
                return 0;
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