public class StudentEnrolment {
    private Student student;
    private Course course;
    private String semester;

    public StudentEnrolment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
    public String shortToString(){
//        This is toString function with the id for student and course
        return "The student with the student id "+this.student.getStudentId()+" enroll the class with the class ID "+ course.getClassId()+" in the semester with id "+semester;
    }
    public String FullToString(){
//        this is toString function which will show the full information of student and course
        return student.toString()+" enroll the class "+ course.toString()+" in the semester with id "+semester;
    }
}
