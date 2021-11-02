package tuition;

/**
 * This class is specifically used for students who reside in NJ.
 * It is a subclass of Student and overrides the tuitionDue methods.
 * @author Daniel Flts, Alyssa Santos
 */
public class Resident extends Student {
    private double fullTimeResidentTuition = 12536;
    private double partTimeResidentTuition = 404;


    public Resident(String[] token) {
        super(token);
    }

    /**
     * Method that wil be implemented in Student's subclasses.
     * Calculates the tuition due for a student who is an NJ resident
     */
    @Override
    public void tuitionDue() {
        if ((getCreditHours() >= 12) && (getCreditHours() <= 16)) { //full time student
            this.setTuition(fullTimeResidentTuition + getUniversityFee() - getTotalPayment() - getFinancialAid());
        } else if ((getCreditHours() > 16)) { //exceeds 16 credits
            this.setTuition(fullTimeResidentTuition + getUniversityFee() + (partTimeResidentTuition * (getCreditHours() - 16)) - getTotalPayment() - getFinancialAid());
        } else if (getCreditHours() < 12) { //parttime student
            this.setTuition((partTimeResidentTuition * getCreditHours()) + (getUniversityFee() * 0.8) - getTotalPayment() - getFinancialAid());
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