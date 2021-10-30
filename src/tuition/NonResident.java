package tuition;

import java.text.DecimalFormat;

/**
 * This class is specifically used for students who do not reside in NJ.
 * It is a subclass of Student and overrides the tuitionDue methods.
 * @author Daniel Flts, Alyssa Santos
 */
public class NonResident extends Student {
    public static double fullTimeNonResidentTuition = 29737;
    public static double partTimeNonResidentTuition = 966;

    /**
     * Constructor for NonResident class
     * @param String array from user command line
     */
    public NonResident(String[] token) {
        super(token);
    }

    /**
     * Method that wil be implemented in Student's subclasses.
     * Calculates the tuition due for a student who is not an NJ resident
     */
    @Override
    public void tuitionDue() {
        if ((getCreditHours() >= 12) && (getCreditHours() <= 16)) { //full time student
            this.setTuition(fullTimeNonResidentTuition + getUniversityFee() - getTotalPayment());
        } else if ((getCreditHours() > 16)) { //exceeds 16 credits
            this.setTuition(fullTimeNonResidentTuition + getUniversityFee() + (partTimeNonResidentTuition * (getCreditHours() - 16)) - getTotalPayment());
        } else if (getCreditHours() < 12) { //parttime student
            this.setTuition((partTimeNonResidentTuition * getCreditHours()) + (getUniversityFee() * 0.8) - getTotalPayment());
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