import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class EnrolmentManager {
//    private boolean Enrol(){
//
//    }

    private StudentEnrolmentList studentEnrolmentList=new StudentEnrolmentList();
    private AcademicAssistant academicAssistant=new AcademicAssistant();
    private ArrayList<String> studentIDList = new ArrayList<String>();
    private ArrayList<String> courseNameList = new ArrayList<String>();
    private ArrayList<String> semesterList = new ArrayList<String>();
    public  < E > void addToList(ArrayList<E> addedList,E data){
        if (!addedList.contains(data)){
            addedList.add(data);
        }
    }
    public  < E > boolean isContained(ArrayList<E> addedList,E data){
        return addedList.contains(data);
    }
    public  boolean onlyContainAlpha(String s){
        return  s.matches("[a-zA-Z]+");
    }
    public  boolean onlyContainNumber(String s){
        return  s.matches("[0-9]+");
    }
    public  boolean containAlphaNum(String s){
        return s != null && s.matches("^[a-zA-Z0-9]*$");
    }
    public  void enroll(){
        Scanner scanner= new Scanner(System.in);
        while (true){
            boolean isStop=false;
            while (true){
                System.out.println("please press 0 if you want to stop enter input, else press 1");
                String message=scanner.nextLine();
                if (message.length()==1){
                    if (message.charAt(0)=='0' ){
                        isStop=true;
                        break;
                    }else if ( message.charAt(0)=='1') {
                        String studentId=".";
                        System.out.println("please enter the id of student");
                        studentId=scanner.nextLine();
                        String[] studentIdListString = Arrays.copyOf(
                                studentIDList.toArray(), studentIDList.size(), String[].class);
                        while ( !onlyContainNumber(studentId) && isContained(studentIDList,studentId)){
                            System.out.println("wrong input or this student name is not in the system ");
                            System.out.println("this is the student list "+Arrays.toString(studentIdListString));

                            studentId=scanner.nextLine();
                        }
                        String semester=".";
                        System.out.println("please enter the semester");
                        String[] semesterListString = Arrays.copyOf(
                                semesterList.toArray(), semesterList.size(), String[].class);
                        semester=scanner.nextLine();
                        while ( !containAlphaNum(semester) && isContained(semesterList,semester)){
                            System.out.println("wrong input or this semester not in the system");
                            System.out.println("this is the semester list "+Arrays.toString(semesterListString));

                            semester=scanner.nextLine();
                        }

                        String course=".";
                        System.out.println("please enter the course");
                        String[] courseListString = Arrays.copyOf(
                                courseNameList.toArray(), courseNameList.size(), String[].class);
                        course=scanner.nextLine();
                        while ( !onlyContainAlpha(course) && isContained(courseNameList,course)){
                            System.out.println("wrong input or this course not in the system");
                            System.out.println("this is the course list "+Arrays.toString(courseListString));
                            course=scanner.nextLine();
                        }
                        boolean isExpectedStudentAndCourse=false;
                        for (Student s:academicAssistant.getStudentList()){

                            if (s.getStudentId().equals(studentId)){
                                for (Course c:academicAssistant.getCourseList()){
                                    if (c.getName().equals(course)){
                                        isExpectedStudentAndCourse=true;
                                        StudentEnrolment newStudentEnrolment=new StudentEnrolment(s,c,semester);
                                        academicAssistant.add(newStudentEnrolment);
                                        break;
                                    }
                                }
                            }
                            if (isExpectedStudentAndCourse){
                                break;
                            }
                        }
                        break;
                    }else{
                        continue;
                    }
                }else{
                    continue;
                }
            }

        }

    }
    public static void dataProcessing(){
        try{
            String path="src/data/default.csv";
            BufferedReader bufferedReader =  new BufferedReader(new FileReader(path));
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String[] arrOfStr = line.split(",");
                System.out.println(Arrays.toString(arrOfStr));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        dataProcessing();
    }
}
