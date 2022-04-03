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
    private ArrayList<String> courseIDList = new ArrayList<String>();

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
                                academicAssistant.getSemesterList().toArray(), academicAssistant.getSemesterList().size(), String[].class);
                        semester=scanner.nextLine();
                        while ( !containAlphaNum(semester) && isContained(academicAssistant.getSemesterList(),semester)){
                            System.out.println("wrong input or this semester not in the system");
                            System.out.println("this is the semester list "+Arrays.toString(semesterListString));

                            semester=scanner.nextLine();
                        }

                        String course=".";
                        System.out.println("please enter the courseID");
                        String[] courseListString = Arrays.copyOf(
                                courseIDList.toArray(), courseIDList.size(), String[].class);
                        course=scanner.nextLine();
                        while ( !containAlphaNum(course) && isContained(courseIDList,course)){
                            System.out.println("wrong input or this course not in the system");
                            System.out.println("this is the courseID list "+Arrays.toString(courseListString));
                            course=scanner.nextLine();
                        }
                        boolean isExpectedStudentAndCourse=false;
                        for (Student s:academicAssistant.getStudentList()){

                            if (s.getStudentId().equals(studentId)){
                                for (Course c:academicAssistant.getCourseList()){
                                    if (c.getClassId().equals(course)){
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
    public  void dataProcessing(){
        try{
            String path="src/data/default.csv";
            BufferedReader bufferedReader =  new BufferedReader(new FileReader(path));
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String[] arrOfStr = line.split(",");
                Student newStudent= new Student(arrOfStr[0],arrOfStr[1],arrOfStr[2]);
                Course newCourse= new Course(arrOfStr[3],arrOfStr[4],Integer.parseInt(arrOfStr[5]));
                String newSemester= arrOfStr[6];
                if (!studentIDList.contains(arrOfStr[0])){
                    academicAssistant.getStudentList().add(newStudent);
                    studentIDList.add(arrOfStr[0]);
                }
                if (!courseIDList.contains(arrOfStr[3])){
                    academicAssistant.getCourseList().add(newCourse);
                    courseIDList.add(arrOfStr[3]);
                }
                if (!academicAssistant.getSemesterList().contains(newSemester)){
                    academicAssistant.getSemesterList().add(newSemester);
                }
                StudentEnrolment newStudentEnrolment= new StudentEnrolment(newStudent,newCourse,newSemester);
                if (!academicAssistant.getStudentEnrolmentList().getListStudentEnrolment().contains(newStudentEnrolment)){
                    academicAssistant.add(newStudentEnrolment);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  void main(String[] args) {
        dataProcessing();
    }
}
