import java.util.ArrayList;

public class AcademicAssistant implements StudentEnrolmentManager{
    private StudentEnrolmentList studentEnrolmentList;
    private ArrayList<Student> studentList = new ArrayList<Student>();
    private ArrayList<Course> courseList = new ArrayList<Course>();
    private ArrayList<String> semesterList = new ArrayList<String>();


    public AcademicAssistant() {
        this.studentEnrolmentList = new StudentEnrolmentList();
    }

    public AcademicAssistant(StudentEnrolmentList studentEnrolmentList) {
        this.studentEnrolmentList = studentEnrolmentList;
    }

    public AcademicAssistant(StudentEnrolmentList studentEnrolmentList, ArrayList<Student> studentList, ArrayList<Course> courseList, ArrayList<String> semesterList) {
        this.studentEnrolmentList = studentEnrolmentList;
        this.studentList = studentList;
        this.courseList = courseList;
        this.semesterList = semesterList;
    }

    public StudentEnrolmentList getStudentEnrolmentList() {
        return studentEnrolmentList;
    }

    public void setStudentEnrolmentList(StudentEnrolmentList studentEnrolmentList) {
        this.studentEnrolmentList = studentEnrolmentList;
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public ArrayList<String> getSemesterList() {
        return semesterList;
    }

    public void setSemesterList(ArrayList<String> semesterList) {
        this.semesterList = semesterList;
    }

    @Override
    public boolean add( StudentEnrolment studentEnrolment) {
//        if there is the same StudentEnrolment in our system do not add
        if(!this.studentEnrolmentList.getListStudentEnrolment().contains(studentEnrolment)) {
            this.studentEnrolmentList.getListStudentEnrolment().add(studentEnrolment);

            return true;
        }
        return false;
    }

    @Override
    public boolean update(String courseId,String studentId,String semester,StudentEnrolment updatedStudentEnrolment) {
//        check if there is any StudentEnrollment have the same studentID and courseID an semester in our system, if have update this studentEnrolment wiht the new value
        for  (int i =0;i<this.studentEnrolmentList.getListStudentEnrolment().size();i+=1){
            StudentEnrolment studentEnrolment=this.studentEnrolmentList.getListStudentEnrolment().get(i);
            if (studentEnrolment.getStudent().getStudentId().equals(studentId) && studentEnrolment.getCourse().getClassId().equals(courseId)&& studentEnrolment.getSemester().equals(semester)){
                this.studentEnrolmentList.getListStudentEnrolment().set(i,updatedStudentEnrolment);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean delete(String courseId,String studentId,String semester) {
//        if there is the StudentEnrolment with the same attribute will delete otther wise return false
        for  (int i =0;i<this.studentEnrolmentList.getListStudentEnrolment().size();i+=1){
            StudentEnrolment studentEnrolment=this.studentEnrolmentList.getListStudentEnrolment().get(i);
            if (studentEnrolment.getStudent().getStudentId().equals(studentId) && studentEnrolment.getCourse().getClassId().equals(courseId)&& studentEnrolment.getSemester().equals(semester)){
                this.studentEnrolmentList.getListStudentEnrolment().remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public StudentEnrolment getOne(String courseId,String studentId,String semester) {
//        get the value with same attribut
        for  (int i =0;i<this.studentEnrolmentList.getListStudentEnrolment().size();i+=1){
            StudentEnrolment studentEnrolment=this.studentEnrolmentList.getListStudentEnrolment().get(i);
            if (studentEnrolment.getStudent().getStudentId().equals(studentId) && studentEnrolment.getCourse().getClassId().equals(courseId)&& studentEnrolment.getSemester().equals(semester)){

                return studentEnrolment;
            }
        }
        return null;
    }
    public boolean isContained(String newStudentId,String newCourseId,String newSemester){
//        chekc if this student enrolment have been in our system
        for (StudentEnrolment s:studentEnrolmentList.getListStudentEnrolment()){
            if (s.getSemester().equals(newSemester) && s.getCourse().getClassId().equals(newCourseId) && s.getStudent().getStudentId().equals(newStudentId)){
                return true;
            }
        }
        return false;
    }
    @Override
    public StudentEnrolmentList getAll() {
        return this.getStudentEnrolmentList();
    }
//    return all value


}
