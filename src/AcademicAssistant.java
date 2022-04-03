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
        if(!this.studentEnrolmentList.getListStudentEnrolment().contains(studentEnrolment)) {
            this.studentEnrolmentList.getListStudentEnrolment().add(studentEnrolment);

            return true;
        }
        return false;
    }

    @Override
    public boolean update(String courseId,String studentId,String semester,StudentEnrolment updatedStudentEnrolment) {
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
        for  (int i =0;i<this.studentEnrolmentList.getListStudentEnrolment().size();i+=1){
            StudentEnrolment studentEnrolment=this.studentEnrolmentList.getListStudentEnrolment().get(i);
            if (studentEnrolment.getStudent().getStudentId().equals(studentId) && studentEnrolment.getCourse().getClassId().equals(courseId)&& studentEnrolment.getSemester().equals(semester)){

                return studentEnrolment;
            }
        }
        return null;
    }

    @Override
    public StudentEnrolmentList getAll() {
        return this.getStudentEnrolmentList();
    }


}
