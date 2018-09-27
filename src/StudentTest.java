


/**
 *
 * @author Pedro
 */

import com.sun.corba.se.spi.ior.MakeImmutable;
import java.util.*;

public class StudentTest {
    
    List<Student> StudentList;
    Student[] studentObj;

    private int[] studentIDs = new int[10];
    private int nextID = 0;

    private String[] studentLastNames = new String[10];
    private int[] studentLastNamesPicked = {15, 15, 15, 15, 15, 15, 15, 15, 15, 15};
    private int nextPosition = 0;

    private String[] coursesNames = {"AMH", "CHM", "COP", "MAC"};
    private int[] courseNumbersAHM = new int[3];
    private int[] courseNumbersCHM = new int[3];
    private int[] courseNumbersCOP = new int[3];
    private int[] courseNumbersMAC = new int[3];

    public static void main(String[] args) {
        StudentTest studentCourseList = new StudentTest();
        
    }
    
    public StudentTest() {
        String[] studentLastNames = {"Walker", "Johnson", "Clinton", "Beafils", "Rosales", "Lawence", "Moore", "Rodreguez", "Miller", "Jordan"};
        this.studentLastNames = studentLastNames;
        studentObj = new Student[10];
        StudentList = new ArrayList<Student>();
        String courseEnrollment = "";
        courseMaker();
        for (int i = 0; i < 10; i++) {
            studentObj[i] = makeAStudent();
        }

        addStudentToList();

        sortList();

        displayStudentList();
        
        for(int i = 0; i < 10; i++) {
            courseEnrollment += (studentObj[i].getCourseList(1) + " " + studentObj[i].getCourseList(2)
            + " " + studentObj[i].getCourseList(3) + "\n");
        }
        
        String[] words = courseEnrollment.split("[ \n\t\r,.;:!?(){}]");
        TreeMap<String, Integer> map = new TreeMap<String, Integer>();
        for (int i = 0; i < words.length; i++){
            String key = words[i];
            if (words[i].length() > 1){
                if (map.get(key) == null){
                    map.put(key, 1);
                }
                else {
                    int value = map.get(key).intValue();
                    value++;
                    map.put(key, value);
                }
            }
        }
        System.out.println("----------------- Course Enrollments ------------------------");
        //System.out.println(map);
        printMap(map);
        //System.out.println(courseEnrollment);

    }

    private void courseMaker() {
        Random number = new Random();
        int time = 1;
        int courseNumber1;
        int courseNumber2;
        int courseNumber3;
        for (int i = 0; i < 4; i++) {
            courseNumber1 = number.nextInt(8999);
            courseNumber2 = number.nextInt(8999);
            courseNumber3 = number.nextInt(8999);
            courseNumber1 += 1000;
            courseNumber2 += 1000;
            courseNumber3 += 1000;
            if (courseNumber1 != courseNumber2 && courseNumber2 != courseNumber3) {

                if (time == 1) {
                    courseNumbersAHM[0] = courseNumber1;
                    courseNumbersAHM[1] = courseNumber2;
                    courseNumbersAHM[2] = courseNumber3;
                } else if (time == 2) {
                    courseNumbersCHM[0] = courseNumber1;
                    courseNumbersCHM[1] = courseNumber2;
                    courseNumbersCHM[2] = courseNumber3;
                } else if (time == 3) {
                    courseNumbersMAC[0] = courseNumber1;
                    courseNumbersMAC[1] = courseNumber2;
                    courseNumbersMAC[2] = courseNumber3;
                } else if (time == 4) {
                    courseNumbersCOP[0] = courseNumber1;
                    courseNumbersCOP[1] = courseNumber2;
                    courseNumbersCOP[2] = courseNumber3;
                }
                time++;

            } else {
                i--;
            }
        }
    }

    public Student makeAStudent() {

        String studentID = createStudentID();
        String lastName = studentLastName();
        String[] coursesName = courseGiverName();
        int[] coursesNumber = courseGiverNumber(coursesName);
        Student student = new Student(studentID, lastName, coursesName, coursesNumber);

        return student;
    }

    private String createStudentID() {

        Random number = new Random();
        Boolean same = true;
        int counter = 0;
        int studentID;
        String studentIDToString;

        do {
            counter = 0;
            studentID = number.nextInt(9999) + 1;
            for (int i = 0; i < studentIDs.length; i++) {
                if (studentID != studentIDs[i]) {
                    counter++;
                }
                if (counter == 10) {
                    same = false;
                }
            }
            if (same == false) {
                studentIDs[nextID] = studentID;
                nextID++;
            }
        } while (same == true);

        studentIDToString = Integer.valueOf(studentID).toString();

        if (studentID < 1000) {
            if (studentID < 10) {
                studentIDToString = "000" + Integer.valueOf(studentID).toString();
            } else if (studentID < 100) {
                studentIDToString = "00" + Integer.valueOf(studentID).toString();
            } else {
                studentIDToString = "0" + Integer.valueOf(studentID).toString();
            }
        }

        return studentIDToString;

    }

    private String studentLastName() {

        String lastName = null;
        Random number = new Random();
        int lastNamePosition;
        int counter = 0;
        boolean lastNameHasntBeenPicked = true;

        do {
            counter = 0;
            lastNamePosition = number.nextInt(10);

            for (int i = 0; i < studentLastNamesPicked.length; i++) {
                if (lastNamePosition != studentLastNamesPicked[i]) {
                    counter++;
                }
            }

            if (counter == 10) {
                lastNameHasntBeenPicked = false;
            }

            if (lastNameHasntBeenPicked == false) {
                studentLastNamesPicked[nextPosition] = lastNamePosition;
                studentIDs[nextPosition] = lastNamePosition;
                nextPosition++;
            }
        } while (lastNameHasntBeenPicked == true);

        lastName = studentLastNames[lastNamePosition];

        return lastName;
    }

    private String[] courseGiverName() {
        String[] coursesNamesArray = new String[3];
        Random number = new Random();
        int courseNamePosition;
        String courseName;

        for (int i = 0; i < 3; i++) {
            courseNamePosition = number.nextInt(4);
            courseName = coursesNames[courseNamePosition];

            coursesNamesArray[i] = courseName;

            if (i == 1) {
                if (coursesNamesArray[i].equals(coursesNamesArray[0])) {
                    i--;
                }
            } else if (i == 2) {
                if (coursesNamesArray[i].equals(coursesNamesArray[0]) || coursesNamesArray[i].equals(coursesNamesArray[1])) {
                    i--;
                }
            }
        }

        return coursesNamesArray;
    }

    private int[] courseGiverNumber(String[] courseNamesArray) {
        int[] courseNumber = new int[3];
        Random number = new Random();
        int courseNumberPosition;
        int currentCourseNumber = 0;

        for (int i = 0; i < 3; i++) {

            courseNumberPosition = number.nextInt(3);

            if (courseNamesArray[i].equals("AMH")) {
                currentCourseNumber = courseNumbersAHM[courseNumberPosition];
            } else if (courseNamesArray[i].equals("CHM")) {
                currentCourseNumber = courseNumbersCHM[courseNumberPosition];
            } else if (courseNamesArray[i].equals("COP")) {
                currentCourseNumber = courseNumbersCOP[courseNumberPosition];
            } else if (courseNamesArray[i].equals("MAC")) {
                currentCourseNumber = courseNumbersMAC[courseNumberPosition];
            }
            courseNumber[i] = currentCourseNumber;

            if (i == 1) {
                if (courseNumber[i] == courseNumber[0]) {
                    i--;
                }
            } else if (i == 2) {
                if (courseNumber[i] == courseNumber[0] || courseNumber[i] == courseNumber[1]) {
                    i--;
                }
            }
        }

        return courseNumber;
    }

    public void addStudentToList() {
        for (int a = 0; a < studentObj.length; a++) {
            StudentList.add(studentObj[a]);
        }
    }

    public void sortList() {
        Collections.sort(StudentList, new Comparator() {
            @Override
            public int compare(Object StdObj1, Object StdObj2) {
                return ((Student) StdObj1).getLastName()
                        .compareTo(((Student) StdObj2).getLastName());
            }
        });
    }

    
    public void displayStudentList() {
        for (int a = 0; a < StudentList.size(); a++) {
            System.out.println(StudentList.get(a));
        }
    }
    
    public static <K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry.getKey()
				+ " -- " + entry.getValue());
        }
    }
    
}
