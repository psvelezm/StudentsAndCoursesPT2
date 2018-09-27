

/**
 *
 * @author Pedro
 */
public class Student {
    
    private String studentID;
    private String lastName = null;
    private String[] courseName = new String[3];
    private int[] courseNumber = new int[3];

    Student(String studentID, String lastName, String[] courseName, int[] courseNumber) {
        this.studentID = studentID;
        this.lastName = lastName;
        this.courseName = courseName;
        this.courseNumber = courseNumber;
    }

    public String getStudentID() {
        return this.studentID;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getCourseList(int whichOne) {
        String courseList = null;

        if (whichOne == 1) {
            courseList = courseName[0]  + Integer.valueOf(courseNumber[0]).toString();
        } else if (whichOne == 2) {
            courseList = courseName[1]  + Integer.valueOf(courseNumber[1]).toString();
        } else {
            courseList = courseName[2]  + Integer.valueOf(courseNumber[2]).toString();
        }
        return courseList;
    }

    public String[] getCourseName() {
        return this.courseName;
    }

    public int[] getCourseNumber() {
        return this.courseNumber;
    }

    
    public String toString() {
        return studentID + ", " + lastName + "\t" + getCourseList(1) + ", " + getCourseList(2) + ", " + getCourseList(3);
    }
    
}
