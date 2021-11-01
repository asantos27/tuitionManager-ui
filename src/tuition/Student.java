package tuition;
import java.text.DecimalFormat;

/**
 * This is the parent class that defines all common data items and operations for all student instances
 * @author Daniel Flts, Alyssa Santos
 */
public class Student {
    public Profile studentProfile;
    private String residencyStatus;
    private int creditHours;
    private double totalPayment = 0;
    private double lastPaymentAmount;
    private Date lastPaymentDate;
    private double tuition;
    private String state; //NY or CT
    private boolean studyAbroad;
    private double financialAid = 0;


    /**
     * Constructor for Student class
     * @param String array from user command line
     */
    public Student(String[] token) {
        this.setStudentProfile(token);
    }

    /**
     * Setter method to set the residency of a student
     * @param residency of student
     */
    public void setResidencyStatus(String residencyStatus) {
        this.residencyStatus = residencyStatus;
    }

    /**
     * Setter method to get the residency of a student
     * @return number of student's credit hours
     */
    public String getResidencyStatus() {
        return this.residencyStatus;
    }

    /**
     * Method that turns String into Object
     * @param major string that will become Major Object
     */
    private Major toMajor(String major) {
        switch(major.toUpperCase()) {
            case "CS":
                return Major.CS;
            case "IT":
                return Major.IT;
            case "BA":
                return Major.BA;
            case "EE":
                return Major.EE;
            case "ME":
                return Major.ME;
            default:
                return Major.Unknown;
        }
    }

    /**
     * Setter method to set the profile of the student
     * @param Profile of a student
     */
    public void setStudentProfile(String[] tokens) {
        studentProfile = new Profile();
        studentProfile.setName(tokens[0]);
        studentProfile.setMajor(toMajor(tokens[1]));
    }

    /**
     * Getter method to get the profile of a student
     * @return profile of student
     */
    public Profile getStudentProfile() {
        return this.studentProfile;
    }

    /**
     * Setter method to set the credit hours of a student
     * @param number of student's credit hours
     */
    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    /**
     * Getter method to get the number of credit hours of a student
     * @return number of student's credit hours
     */
    public int getCreditHours() {
        return this.creditHours;
    }


    /**
     * Getter method to get the latest amount a student has paid
     * @return amount a student has paid
     */
    public double getLastPaymentAmount() {
        return this.lastPaymentAmount;
    }

    /**
     * Setter method to set the amount a student has paid
     * @param amount a student has paid
     */
    public void setLastPaymentAmount(double lastPaymentAmount) {
        this.lastPaymentAmount = lastPaymentAmount;
    }

    /**
     * Setter method to set the amount a student has paid
     * @param amount a student has paid
     */
    public void setTotalPayment(double totalPayment) {
        this.totalPayment += totalPayment;
    }

    /**
     * Getter method to get the amount a student has paid
     * @return amount a student has paid
     */
    public double getTotalPayment() {
        return this.totalPayment;
    }

    /**
     * Setter method to set the date of a student's latest payment
     * @param date of a student's latest payment
     */
    public void setLastPaymentDate(Date lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    /**
     * Getter method to get the date of a student's latest payment
     * @return date of a student's latest payment
     */
    public Date getLastPaymentDate() {
        return this.lastPaymentDate;
    }

    /**
     * Setter method to set the tuition of a student
     * @param student's tuition
     */
    public void setTuition(Double tuition) {
        this.tuition = tuition;
    }

    /**
     * Getter method to get the tuition of a student
     * @return student's tuition
     */
    public double getTuition() {
        return this.tuition;
    }

    /**
     * Setter method to set the state of TriState student
     * @param State of student
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Getter method to set the state of TriState student
     * @return State of student
     */
    public String getState() {
        return this.state;
    }

    /**
     * Setter method to indicate if a student is participating in the study abroad program
     * @param boolean of if student studying in study abroad program
     */
    public void setStudyAbroad(boolean studyAbroad) {
        this.studyAbroad = studyAbroad;
    }

    /**
     * Getter method to indicate if a student is participating in the study abroad program
     * @return true if student is studying abroad, false if not
     */
    public boolean getStudyAbroad() {
        return this.studyAbroad;
    }


    /**
     * Getter method to get university fee
     * @return university fee
     */
    public double getUniversityFee() {
        return 3268;
    }

    /**
     * Setter method that sets the amount for the student's one time financial aid
     * @param amount of financial aid received
     */
    public void setFinancialAid(Double financialAid) {
        this.financialAid = financialAid;
    }

    /**
     * Getter method that sets the amount for the student's one time financial aid
     * @return amount of financial aid received
     */
    public double getFinancialAid() {
        return financialAid;
    }

    /**
     * Overrides toString method. Returns concatenation of Student profile and other information
     * @return String of Student information
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("###,##0.00");
        String studentString = getStudentProfile() + ":" + getCreditHours() + " credit hours:tuition due:"
                + df.format(getTuition()) + ":total payment:" + df.format(getTotalPayment()) + ":last payment date: ";

        if (getResidencyStatus() == "resident") {
            String financialAid = "";
            if (getFinancialAid() != 0) {
                financialAid = ":financial aid $" + getFinancialAid();
            }

            if (getLastPaymentDate() == null) {
                studentString += "--/--/--" + ":resident" + financialAid;
            } else {
                studentString += getLastPaymentDate() + ":resident" + financialAid;
            }

        } else if (getResidencyStatus() == "nonresident") {
            if (getLastPaymentDate() == null) {
                studentString += "--/--/--" + ":non-resident";
            } else {
                studentString += getLastPaymentDate() + ":non-resident";
            }

        } else if (getResidencyStatus() == "tristate") {
            if (getLastPaymentDate() == null) {
                studentString += "--/--/--" + ":non-resident(tri-state):" + getState();
            } else {
                studentString += getLastPaymentDate() + ":non-resident(tri-state):" + getState();
            }

        } else if (getResidencyStatus() == "international") {
            if (getStudyAbroad() == true) {
                if (getLastPaymentDate() == null) {
                    studentString += "--/--/--" + ":non-resident:international:study abroad";
                } else {
                    studentString += getLastPaymentDate() + ":non-resident:international:study abroad";
                }

            } else {
                if (getLastPaymentDate() == null) {
                    studentString += "--/--/--" + ":non-resident:international";
                } else {
                    studentString += getLastPaymentDate() + ":non-resident:international";
                }
            }
        }
        return studentString;
    }

    /**
     * Method that wil be implemented in Student's subclasses.
     * Calculates the tuition due for each student based on their residency status
     */
    public void tuitionDue() {}

    /**
     * Method that wil be implemented in International subclasses.
     * Set's study abroad status
     */
    public void setStudyAbroad() {}
}