import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.stream.Collectors;
public class EnrolmentManager {
//    private boolean Enrol(){
//
//    }

    private StudentEnrolmentList studentEnrolmentList=new StudentEnrolmentList();
    private AcademicAssistant academicAssistant=new AcademicAssistant();
    private ArrayList<String> studentIDList = new ArrayList<String>();
    private ArrayList<String> courseIDList = new ArrayList<String>();


    public EnrolmentManager() {

    }

    public  < E > void addToList(ArrayList<E> addedList, E data){
        if (!addedList.contains(data)){
            addedList.add(data);
        }
    }

    public  < E > boolean isContained(ArrayList<E> addedList,E data){
        return addedList.contains(data);
    }

    public < E > ArrayList<ArrayList<E>>  listing(ArrayList<E> val, ArrayList<E> vall){

// Listing the value based on another two value, ex: we can listing the list of course based on the student enroll this course in this semester
        ArrayList<ArrayList<E>> ans = new ArrayList<ArrayList<E>>();
        for(E val1:val){
            for (E val2:vall){
                for (StudentEnrolment temp:academicAssistant.getStudentEnrolmentList().getListStudentEnrolment()){
                    ArrayList<E> arr=new ArrayList<E>();
                    arr.add( (E)temp.getCourse());
                    arr.add( (E)temp.getStudent());
                    arr.add( (E)temp.getSemester());
                    for (int i=0;i<3;i+=1){
                        if (arr.get(i).equals(val1)){
                            arr.remove(i);
                            break;
                        }
                    }
//            System.out.print(arr.size()+" ");
                    for (int i=0;i<arr.size();i+=1){
                        if (arr.get(i).equals(val2)){
                            arr.remove(i);
                            break;
                        }
                    }
                    if (arr.size()==1){
                        ans.add(arr);
                    }
//            System.out.println(arr.size());
                }
//        if (ans.size()==0){
//            System.out.println("2 values for listing can be not in our system");
//        }
            }
        }

        return ans;
    }

    public Student searchStudentById(String studentId){
//        return the Student Object which have this studentId
        for (Student s:academicAssistant.getStudentList()){
            if (s.getStudentId().equals(studentId)){
                return s;
            }
        }
        return null;
    }
    public Course searchCourseById(String courseId){
//        return Course Object which has this courseID
        for (Course s:academicAssistant.getCourseList()){
            if (s.getClassId().equals(courseId)){
                return s;
            }
        }
        return null;
    }
    public  void updateEnrolmentOfStudent(Scanner scanner,int indexInEnrollmentList){
//Updating every value of a enrollment
        String message="a";
        while (true){
            System.out.println("which information you want to change: "
                    +"\n0 => exit this function"
                    +"\n1 => Student"
                    +"\n2 => Course"
                    +"\n3 => Semester");
            message=scanner.nextLine();
            if (message.length()==1 && (message.charAt(0) == '0'|| message.charAt(0) == '1' || message.charAt(0) == '2' || message.charAt(0) == '3')) {
                 if ( message.charAt(0) == '0'){
                    break;
                }
                else if ( message.charAt(0) == '1'){

                    String newStudentId=enterStudent(scanner);
                    academicAssistant.getStudentEnrolmentList().getListStudentEnrolment().get(indexInEnrollmentList).setStudent(searchStudentById(newStudentId));

                }else if ( message.charAt(0) == '2'){

                     String newCourseId=enterCourse(scanner);
                     academicAssistant.getStudentEnrolmentList().getListStudentEnrolment().get(indexInEnrollmentList).setCourse(searchCourseById(newCourseId));


                }else{

                     String newSemesterId=enterSemester(scanner);
                     academicAssistant.getStudentEnrolmentList().getListStudentEnrolment().get(indexInEnrollmentList).setSemester(newSemesterId);

                }
            }
            else{
                continue;
            }

        }

    }
    public String enterSemester(Scanner scanner){
//        get the semester from user
        String semester=".";
        System.out.println("please enter the semester");
        String[] semesterListString = Arrays.copyOf(
                academicAssistant.getSemesterList().toArray(), academicAssistant.getSemesterList().size(), String[].class);
        semester=scanner.nextLine();
        while (!isContained(academicAssistant.getSemesterList(),semester)){
            System.out.println("wrong input or this semester not in the system");
            System.out.println("this is the semester list "+Arrays.toString(semesterListString));

            semester=scanner.nextLine();
        }
        return semester;
    }
    public String enterCourse(Scanner scanner){
//        get course from user
        String course=".";
        System.out.println("please enter the courseID");
        String[] courseListString = Arrays.copyOf(
                courseIDList.toArray(), courseIDList.size(), String[].class);

        course=scanner.nextLine();
        while (  !isContained(courseIDList,course)){
            System.out.println("wrong input or this course not in the system");
            System.out.println("this is the courseID list "+Arrays.toString(courseListString));
            course=scanner.nextLine();
        }
        return course;
    }
    public  void enroll(Scanner scanner){
//    enroll a student with a a course in a semester
        boolean isStop=false;
        while (isStop==false){
                System.out.println("please press 0 if you want to stop enter input, else press 1");
                String message=scanner.nextLine();
                if (message.length()==1){
                    if (message.charAt(0)=='0' ){
                        isStop=true;
                        break;
                    }else if ( message.charAt(0)=='1')
                    {
                        String studentId=enterStudent(scanner);

                        String semester=enterSemester(scanner);


                        String course=enterCourse(scanner);
                        boolean isExpectedStudentAndCourse=false;
                        StudentEnrolment newStudentEnrollment=new StudentEnrolment(searchStudentById(studentId),searchCourseById(course),semester);
                        if (academicAssistant.isContained(studentId,course,semester)){
                            System.out.println("this Enrollment have in our system");
                        }else{
                            academicAssistant.add(newStudentEnrollment);
                            System.out.println("Enroll Successfully");
                        }
                    }else{
                        continue;
                    }
                }else{
                    continue;
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
                else{
                    newStudent=searchStudentById(arrOfStr[0]);
                }
                if (!courseIDList.contains(arrOfStr[3])){
                    academicAssistant.getCourseList().add(newCourse);
                    courseIDList.add(arrOfStr[3]);
                }
                else{
                    newCourse=searchCourseById(arrOfStr[3]);
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
    public void addNewEnrolmentOfStudent(Scanner scanner,int indexInEnrollmentList){
        System.out.println("please enter your new course with courseId else press 0 to break");
        while (true){
            String newCourseID=scanner.nextLine();
            if (newCourseID.length()==1&& newCourseID.charAt(0)=='0'){
                return;
            }
            if(!academicAssistant.getCourseList().contains(newCourseID)){
                System.out.println("This course ID is not offered in our system, enter your new course with courseId else press 0 to break ");
                continue;
            }else{

//                if (listing())
            }
        }

    };
    public <E> void modify(Scanner scanner){
        System.out.println("This is the list of enrolment in our system, sorted by student and semester");
        for (String st:studentIDList){
            for (String se: academicAssistant.getSemesterList()){
                ArrayList<Object> tempA= new  ArrayList<Object>();
                ArrayList<Object> tempB= new  ArrayList<Object>();
                tempA.add((E)searchStudentById(st));
                tempB.add((E) se);
                ArrayList<ArrayList<Object>> arr=listing(tempA,tempB);
                if (arr.size()>0){
                    System.out.println("This is the list of enrolment in our system of "+st.toString()+" in "+se);
                    for (Object i: arr){
                        System.out.println("\t"+i.toString());
                    }
                }

            }
        }
        boolean isStopChosing=false;
        String expectedStudentId;
        String expectedSemester;
        int indexInEnrollmentList=0;
        while (isStopChosing==false){
            System.out.println("please enter the studentId to choose to update");
            expectedStudentId=scanner.nextLine();
            System.out.println("please enter the semester to choose to update");
            expectedSemester=scanner.nextLine();
            indexInEnrollmentList=0;
            for (StudentEnrolment s:academicAssistant.getStudentEnrolmentList().getListStudentEnrolment()){
                if (s.getStudent().getStudentId().equals(expectedStudentId) && s.getSemester().equals(expectedSemester)){
                    isStopChosing=true;
                    break;
                }
                indexInEnrollmentList+=1;
            }
            if (isStopChosing==false){
                System.out.println("there is no corresponding enrollment in our system ");
            }

        }
        boolean isStop=false;
        while (isStop==false){
            System.out.println("please press 0 if you want to stop enter input, else press 1 for  update");
            String message=scanner.nextLine();
            if (message.length()==1){
                if (message.charAt(0)=='0' ){
                    isStop=true;
                    break;
                }else if ( message.charAt(0)=='1') {
                    updateEnrolmentOfStudent(scanner,indexInEnrollmentList);

                }else{
                    continue;
                }
            }else{
                continue;
            }
        }
    }
    public String enterStudent(Scanner scanner){
        String studentId=".";
        System.out.println("please enter the id of student");
        studentId=scanner.nextLine();
        String[] studentIdListString = Arrays.copyOf(
                studentIDList.toArray(), studentIDList.size(), String[].class);

        while ( !isContained(studentIDList,studentId)){
            System.out.println("wrong input or this student name is not in the system ");
            System.out.println("this is the student list "+Arrays.toString(studentIdListString));

            studentId=scanner.nextLine();
        }
        return studentId;
    }
    public void AddAndDeleteEnrolment(Scanner scanner){
        String message;
        String updatedStudent=enterStudent(scanner);
        String updatedCourse=enterCourse(scanner);
        String updatedSemester=enterSemester(scanner);

        while (true){
            System.out.println("press 0 if you want to stop, 1 for add and 2 for delete the enrollment ");
            message=scanner.nextLine();
            if (message.length()==1){
                if (message.charAt(0)=='0' || message.charAt(0)=='1' || message.charAt(0)=='2'){
                    if (message.charAt(0)=='0'){
                        break;
                    }else{

                        if (message.charAt(0)=='1'){

                            if(academicAssistant.isContained(updatedStudent,updatedCourse,updatedSemester)){
                                System.out.println("This enrollment have been our system");

                            }else{
                                Student newObjectStudent=searchStudentById(updatedStudent);

                                Course newObjectCourse=searchCourseById(updatedCourse);

                                academicAssistant.add(new StudentEnrolment(newObjectStudent,newObjectCourse,updatedSemester));
                            }

                        }else if (message.charAt(0)=='2'){
                            if(!academicAssistant.isContained(updatedStudent,updatedCourse,updatedSemester)){
                                System.out.println("This enrollment which we need to delete is not in our system");

                            }else{
                                int index=0;
                                for (StudentEnrolment s:academicAssistant.getStudentEnrolmentList().getListStudentEnrolment()){
                                    if (s.getStudent().getStudentId().equals(updatedStudent) && s.getSemester().equals(updatedSemester) && s.getCourse().getClassId().equals(updatedCourse)){
                                        academicAssistant.getStudentEnrolmentList().getListStudentEnrolment().remove(s);
                                        break;
                                    }

                                }
                            }
                        }
                    }
                }else{
                    continue;
                }
            }else{
                continue;
            }
        }

    }
    public <E>void listingToCSV(String filename,ArrayList<E> val1, ArrayList<E> val2) throws IOException {
        List<String> list1 = new ArrayList<>();

        ArrayList<ArrayList<E>> arr=listing(val1,val2);
        for (int i=0;i<arr.size();i+=1){
            List<String> temp = new ArrayList<>();
            for (E j:arr.get(i)){
                temp.add(j.toString());
            }
            String t2=temp.stream().collect(Collectors.joining(","));
            if (!list1.contains(t2)){
                list1.add(t2);
            }


        }
        FileWriter writer = new FileWriter(filename);

        String collect = list1.stream().collect(Collectors.joining("\n"));

        writer.write(collect);
        writer.close();


    }
    public  <E> void main() throws IOException {
        dataProcessing();

        System.out.println("======== Welcome to the Enrolment Management Application ========");
        // Start scanner
        Scanner in = new Scanner(System.in);
        // User need to enroll a student

        while(true) {
            System.out.println("Enter a number to go ahead: "
                    + "\n0 => Enroll a student"
                    + "\n1 => Add/ Delete an enrolment"
                    + "\n2 => Modify an enrolment"
                    + "\n3 => CSV Output ALL courses of ONE student in ONE semester"
                    + "\n4 => CSV Output ALL students of ONE course in ONE semester"
                    + "\n5 => CSV Output ALL courses offered in ONE semester"
                    + "\n6 => Quit");
            String message= in.nextLine();
            if (message.length()==1 && (message.charAt(0)!='0'|| message.charAt(0)!='1'|| message.charAt(0)!='2'||message.charAt(0)!='3'|| message.charAt(0)!='4'|| message.charAt(0)!='5'|| message.charAt(0)!='6')){
                if (message.charAt(0)=='0'){
                    enroll(in);
                }else if (message.charAt(0)=='1'){
                    AddAndDeleteEnrolment(in);
                }else if (message.charAt(0)=='2'){
                    modify(in);
                }else if (message.charAt(0)=='3'){
                    ArrayList<E> a= new  ArrayList<E>();
                    ArrayList<E> b= new  ArrayList<E>();
                    String newStudent=enterStudent(in);
                    String newSemester=enterSemester(in);
                    a.add((E)searchStudentById(newStudent));
                    b.add((E)newSemester);
                    listingToCSV("CoursesPerAStudentInASemeser.csv",a,b);
                }else if (message.charAt(0)=='4'){
                    ArrayList<E> a= new  ArrayList<E>();
                    ArrayList<E> b= new  ArrayList<E>();

                    String newCourse=enterCourse(in);
                    String newSemester=enterSemester(in);
                    a.add((E)searchCourseById(newCourse));
                    b.add((E)newSemester);
                    listingToCSV("StudentPerACourseInASemester.csv",a,b);
                }else if (message.charAt(0)=='5'){
                    ArrayList<E> a= new  ArrayList<E>();
                    ArrayList<E> b= new  ArrayList<E>();


                    String newSemester=enterSemester(in);
                    for(Student s:academicAssistant.getStudentList()){
                        a.add((E)s);
                    }
                    b.add((E)newSemester);
                    listingToCSV("AllCourseInOneSemester.csv",a,b);
                }else if (message.charAt(0)=='6'){
                    break;
                }

            }else{
                System.out.println("wrong input ");
                continue;
            }
        }


    }
}

