package tuition;

/**
 * This class creates the student array, roster. Includes methods such as add, remove
 * @author Daniel Flts, Alyssa Santos
 */
public class Roster {
    private Student[] roster;
    private int size; //keep track of the number of students in the rosster

    /**
     * Constructor to create a new roster of students.
     * Includes the number of students in the roster.
     */
    public Roster() {
        this.roster = new Student[4];
        this.size = 0;
    }

    /**
     * Helper method to check if a student exists in the roster
     * @param student to be found
     * @return true if student is available, false if student is not available
     */
    public boolean isStudentAvailable(Student student) {
        if (this.find(student) == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method to find album in collection
     * @param student to be found
     * @return index of student, -1 if not found
     */
    private int find(Student student) {
        for (int i = 0; i < this.size; i++) {
            if ((this.roster[i].studentProfile.equals(student.studentProfile))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Helper method that returns a specific student from the roster
     * @param student to find
     * @return student in the roster
     */
    public Student getStudent(Student student) {
        return roster[this.find(student)];
    }

    /**
     * Method to increase the capacity of the roster by 4
     */
    private void grow() {
        Student[] newRoster = new Student[this.roster.length + 4];
        int i = 0;
        for (Student student : this.roster) {
            newRoster[i] = student;
            i++;
        }
        this.roster = newRoster;
    }

    /**
     * Adds student to roster
     * @param student to add
     * @return true if student was successfully added, false if student already exists
     */
    public boolean add(Student student) {
        grow();
        this.roster[size++] = student;
        return true;
    }

    /**
     * Removes student from roster
     * @param student
     * @return
     */
    public boolean remove(Student student) {
        int removedStudent = find(student);
        if (removedStudent > -1) { //student exists
            this.roster[removedStudent] = this.roster[this.size - 1];
            this.roster[roster.length - 1] = null;
            this.size--;
            return true;
        }
        return false;
    }

    /**
     * Calculates the tuition of every student in the roster
     */
    public void calculateTuitionOfEveryStudent() {
        for (int i = 0; i < this.size; i++) {
            roster[i].tuitionDue();
        }
    }

    /**
     * Prints the roster as is
     */
    public void print() {
        if (this.size == 0) {
            System.out.println("Student roster is empty!");
        } else {
            System.out.println("* list of students in the roster **");
            for (int i = 0; i < this.size; i++) {
                System.out.println(this.roster[i].toString());
            }
            System.out.println("* end of roster **");
        }
    }

    /**
     * Sorts accounts by last name of holder in ascending order
     */
    private void sortByLastName() {
        for(int i = 1; i < size; i++){
            Student temp = this.roster[i];
            int j = i - 1;
            while(j >= 0 && this.roster[j].studentProfile.getName().toUpperCase().compareTo(temp.studentProfile.getName().toUpperCase()) > 0){
                this.roster[j + 1] = this.roster[j];
                j--;
            }
            this.roster[j + 1] = temp;
        }
    }

    /**
     * Prints the roster sorted by student names
     */
    public void printByStudentName() {
        if (this.size == 0) {
            System.out.println("Student roster is empty!");
        }
        this.sortByLastName();
        System.out.println("* list of students ordered by name **");
        for (int i = 0; i < size; i++) {
            System.out.println(this.roster[i].toString());
        }
        System.out.println("* end of roster **");
    }


    /**
     * Prints only the students who have made payments, ordered by the payment date
     */
    public void printByPaymentDate() {
        if (this.size == 0) {
            System.out.println("Student roster is empty!");
        }
        System.out.println("* list of students made payments ordered by payment date **");
        for (int i = 0; i < this.size; i++) {
            System.out.println(this.roster[i].toString());
        }
        System.out.println("* end of roster **");
    }
}