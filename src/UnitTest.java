
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import org.junit.jupiter.api.Test;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
public class UnitTest {
    EnrolmentManager e= new EnrolmentManager();

    InputStream sysInBackup = System.in;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    public  void test_DataProcessing()throws Exception{
        System.out.println("Test Data Handle");
        int sizeOfInput=15;

        assertEquals(sizeOfInput, e.getAcademicAssistant().getStudentEnrolmentList().getListStudentEnrolment().size(),
                "Get the right size for input from csv file");
    }
    @Test
    public void test_SearchStudentByID(){
        System.out.println("Test search Student by his/her id");
        Student newStudent= e.searchStudentById("S103912");
        assertEquals(newStudent.getStudentId(), "S103912");
        assertEquals(newStudent.getName(), "Son Thanh Le");
        assertEquals(newStudent.getBirthdate(), "2/9/2001");
    }
    @Test
    public void test_SearchCourseByID(){
        System.out.println("Test search Course by id");
        Course newCourse= e.searchCourseById("COSC3321");
        assertEquals(newCourse.getClassId(), "COSC3321");
        assertEquals(newCourse.getName(), "Artificial Intelligence");
        assertEquals(newCourse.getNumOfCredits(), 3);
    }
    @Test
    public void test_EnterStudent(){
        System.out.println("Test Enter Student");
        String input = "S101312\nAlex Mike\n10/13/1998";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals("S101312", e.enterStudent(new Scanner(in)));
    }
    @Test
    public void test_EnterCourse(){
        System.out.println("Test Enter Course");
        String input = "BUS2232\nBusiness Law\n3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals("BUS2232", e.enterCourse(new Scanner(in)));
    }
    @Test
    public void test_EnterSemester(){
        System.out.println("Test Enter Semester");
        String input = "2020C";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals("2020C", e.enterSemester(new Scanner(in)));
    }
    @Test
    public <E> void test_ListingStudentAndCourse(){
        System.out.println("Test with students and course as parameters");
        ArrayList<E> a=new ArrayList<E>();
        ArrayList<E> b=new ArrayList<E>();
        Student s=e.searchStudentById("S103192");
        Course c=e.searchCourseById("BUS2232");
        a.add((E)s);
        b.add((E)c);
        ArrayList<ArrayList<E>> ans=e.listing(a,b);
        assertEquals(1, ans.size());
    }
    @Test
    public <E> void test_ListingSemesterAndCourse(){
        System.out.println("Test with semester and course as parameters");
        ArrayList<E> a=new ArrayList<E>();
        ArrayList<E> b=new ArrayList<E>();
        String s="2021A";
        Course c=e.searchCourseById("BUS2232");
        a.add((E)s);
        b.add((E)c);
        ArrayList<ArrayList<E>> ans=e.listing(a,b);
        assertEquals(0, ans.size());
    }
    @Test
    public <E> void test_ListingSemesterAndStudent(){
        System.out.println("Test with semester and student as parameters");
        ArrayList<E> a=new ArrayList<E>();
        ArrayList<E> b=new ArrayList<E>();
        String s="2021A";
        Student c=e.searchStudentById("S101153");
        a.add((E)s);
        b.add((E)c);
        ArrayList<ArrayList<E>> ans=e.listing(a,b);
        assertEquals(1, ans.size());
    }
    @Test
    public void test_enroll(){
        System.out.println("Test enroll");
        String input = "0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        e.enroll(new Scanner(System.in));
        assertEquals("Test enroll\n" +
                "please press 0 if you want to stop enter input, else press 1\n" +
                "you have stopped this function", outputStreamCaptor.toString()
                .trim());
    }
    @Test
    public void test_enroll_with_existing_enrolment(){
        System.out.println("Test enroll with existing enrollemnt");
        String input = "1\nS101312\n2020C\nBUS2232\n0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        e.enroll(new Scanner(System.in));
        assertEquals("Test enroll with existing enrollemnt\n" +
                "please press 0 if you want to stop enter input, else press 1\n" +
                "please enter the id of student\n" +
                "please enter the semester\n" +
                "please enter the courseID\n" +
                "this Enrollment have in our system\n" +
                "please press 0 if you want to stop enter input, else press 1\n" +
                "you have stopped this function", outputStreamCaptor.toString()
                .trim());
    }
    @Test
    public void test_enroll_with_no_existing_enrolment(){
        System.out.println("Test enroll with no existing enrollemnt");
        String input = "1\nS101312\n2021A\nBUS2232\n0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        e.enroll(new Scanner(System.in));
        assertEquals("Test enroll with no existing enrollemnt\n" +
                "please press 0 if you want to stop enter input, else press 1\n" +
                "please enter the id of student\n" +
                "please enter the semester\n" +
                "please enter the courseID\n" +
                "Enroll Successfully\n" +
                "please press 0 if you want to stop enter input, else press 1\n" +
                "you have stopped this function", outputStreamCaptor.toString()
                .trim());
    }
    @Test
    public void Test_AddingNoneExistingEnrolment(){
        System.out.println("Test Adding Enrolment");
        String input = "S101312\nBUS2232\n2021A\n1\n0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        e.AddAndDeleteEnrolment(new Scanner(System.in));
        assertEquals("Test Adding Enrolment\n" +
                "please enter the id of student\n" +
                "please enter the courseID\n" +
                "please enter the semester\n" +
                "press 0 if you want to stop, 1 for add and 2 for delete the enrollment \n" +
                "adding function succesfully\n" +
                "press 0 if you want to stop, 1 for add and 2 for delete the enrollment \n" +
                "you have stopped this function", outputStreamCaptor.toString()
                .trim());
    }
    @Test
    public void Test_AddingExistingEnrolment(){
        System.out.println("Test Adding Enrolment");
        String input = "S101312\nPHYS1230\n2021A\n1\n0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        e.AddAndDeleteEnrolment(new Scanner(System.in));
        assertEquals("Test Adding Enrolment\n" +
                "please enter the id of student\n" +
                "please enter the courseID\n" +
                "please enter the semester\n" +
                "press 0 if you want to stop, 1 for add and 2 for delete the enrollment \n" +
                "This enrollment have been our system\n" +
                "press 0 if you want to stop, 1 for add and 2 for delete the enrollment \n" +
                "you have stopped this function", outputStreamCaptor.toString()
                .trim());
    }
    @Test
    public void test_SearchExistingStudentEnrollment(){
        System.out.println("Test search Student enrollment");
        StudentEnrolment newStudent= e.getAcademicAssistant().getOne("BUS2232","S103912","2020C");
        assertEquals("BUS2232",newStudent.getCourse().getClassId());
        assertEquals("S103912",newStudent.getStudent().getStudentId());
        assertEquals("2020C",newStudent.getSemester());

    }
    @Test
    public void test_SearchNoneExistingStudentEnrollment(){
        System.out.println("Test search Non Existing Student enrollment");
        StudentEnrolment newStudent= e.getAcademicAssistant().getOne("BUS2232","S103912","2021A");
        assertEquals(null,newStudent);
    }
    @Test
    public void Test_DeleteExistingEnrolment(){
        System.out.println("Test Delete Enrolment");
        String input = "S101312\nPHYS1230\n2021A\n2\n0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        e.AddAndDeleteEnrolment(new Scanner(System.in));
        StudentEnrolment st=e.getAcademicAssistant().getOne("PHYS1230","S101312","2021A");
        assertEquals(null,st);
    }
    @Test
    public void Test_DeleteNonExistingEnrolment(){
        System.out.println("Test Delete Enrolment");
        String input = "S101312\nCOSC3321\n2021A\n2\n0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        e.AddAndDeleteEnrolment(new Scanner(System.in));
        assertEquals("Test Delete Enrolment\n" +
                "please enter the id of student\n" +
                "please enter the courseID\n" +
                "please enter the semester\n" +
                "press 0 if you want to stop, 1 for add and 2 for delete the enrollment \n" +
                "This enrollment which we need to delete is not in our system\n" +
                "press 0 if you want to stop, 1 for add and 2 for delete the enrollment \n" +
                "you have stopped this function", outputStreamCaptor.toString()
                .trim());
    }
    @Test
    public void Test_UpdatingNonExistingEnrolment(){
        System.out.println("Test Updating non Exist Enrolment");
        String input = "S101312\n2021A\nPHYS1230\n1\n2\nCOSC3321\n0\n0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        e.modify(new Scanner(System.in));
        StudentEnrolment st=e.getAcademicAssistant().getOne("COSC3321","S101312","2021A");
        assertEquals(st.getCourse().getClassId(),"COSC3321");
        assertEquals(st.getStudent().getStudentId(),"S101312");
        assertEquals(st.getSemester(),"2021A");
        assertEquals("Test Updating non Exist Enrolment\n" +
                "This is the list of enrolment in our system, sorted by student and semester\n" +
                "This is the list of enrolment in our system of S101312 in 2020C\n" +
                "\t[class id is COSC4030, class name is Theory of Computation with 5 credits.]\n" +
                "\t[class id is BUS2232, class name is Business Law with 3 credits.]\n" +
                "This is the list of enrolment in our system of S101312 in 2021A\n" +
                "\t[class id is PHYS1230, class name is Introductory Human Physiology with 4 credits.]\n" +
                "This is the list of enrolment in our system of S102732 in 2020C\n" +
                "\t[class id is COSC4030, class name is Theory of Computation with 5 credits.]\n" +
                "This is the list of enrolment in our system of S102732 in 2021A\n" +
                "\t[class id is COSC3321, class name is Artificial Intelligence with 3 credits.]\n" +
                "This is the list of enrolment in our system of S103723 in 2020B\n" +
                "\t[class id is BUS2232, class name is Business Law with 3 credits.]\n" +
                "This is the list of enrolment in our system of S103821 in 2021A\n" +
                "\t[class id is PHYS1230, class name is Introductory Human Physiology with 4 credits.]\n" +
                "This is the list of enrolment in our system of S101163 in 2020C\n" +
                "\t[class id is BUS2232, class name is Business Law with 3 credits.]\n" +
                "This is the list of enrolment in our system of S101163 in 2021A\n" +
                "\t[class id is COSC3321, class name is Artificial Intelligence with 3 credits.]\n" +
                "This is the list of enrolment in our system of S101153 in 2021A\n" +
                "\t[class id is COSC3321, class name is Artificial Intelligence with 3 credits.]\n" +
                "This is the list of enrolment in our system of S103817 in 2020C\n" +
                "\t[class id is COSC4030, class name is Theory of Computation with 5 credits.]\n" +
                "This is the list of enrolment in our system of S103817 in 2021A\n" +
                "\t[class id is COSC3321, class name is Artificial Intelligence with 3 credits.]\n" +
                "This is the list of enrolment in our system of S103912 in 2020C\n" +
                "\t[class id is BUS2232, class name is Business Law with 3 credits.]\n" +
                "This is the list of enrolment in our system of S102192 in 2021A\n" +
                "\t[class id is PHYS1230, class name is Introductory Human Physiology with 4 credits.]\n" +
                "This is the list of enrolment in our system of S103192 in 2020B\n" +
                "\t[class id is BUS2232, class name is Business Law with 3 credits.]\n" +
                "please enter the id of student\n" +
                "please enter the semester\n" +
                "please enter the courseID\n" +
                "please press 0 if you want to stop enter input, else press 1 for  update\n" +
                "which information you want to change: \n" +
                "0 => exit this function\n" +
                "1 => Student\n" +
                "2 => Course\n" +
                "3 => Semester\n" +
                "please enter the courseID\n" +
                "which information you want to change: \n" +
                "0 => exit this function\n" +
                "1 => Student\n" +
                "2 => Course\n" +
                "3 => Semester\n" +
                "you have stopped this function\n" +
                "please press 0 if you want to stop enter input, else press 1 for  update", outputStreamCaptor.toString()
                .trim());
    }
}
