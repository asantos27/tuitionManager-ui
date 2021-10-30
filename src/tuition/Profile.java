package tuition;

/**
 * This is class defines a student's profile which includes their name and major.
 * Getter and Setter methods are defined for other classes to access its data.
 * @author Daniel Flts, Alyssa Santos
 */
public class Profile {
    private String name;
    private Major major; //5 majors and 2-character

    /**
     * Setter method to set the name of a student
     * @param name of student
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method to get the name of a student
     * @return name of student
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter method to set the major of a student
     * @param major of student
     */
    public void setMajor(Major major) {
        this.major = major;
    }

    /**
     * Getter method to get the major of a student
     * @return name of student
     */
    public Major getMajor() {
        return this.major;
    }

    /**
     * Compares this student to another to determine if they are equal
     * @param Profile obj to be compared to
     * @return true if they are the same, false otherwise
     */
    //@Override
    public boolean equals(Profile profile) {
        //returns true if the name and major are the same for the two students
        if(profile.name.equals(this.name) && profile.major.equals(this.major)) {
            return true;
        }
        return false;
    }

    /**
     * Concatenates -- into a string
     * @return -- into one string
     */
    @Override
    public String toString() {
        return this.name + ":" + this.major;
    }

}
