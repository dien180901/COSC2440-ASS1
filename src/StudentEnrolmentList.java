import java.util.ArrayList;
public class StudentEnrolmentList {
    private ArrayList<StudentEnrolment> listStudentEnrolment = new ArrayList<StudentEnrolment>();

    public StudentEnrolmentList() {
        this.listStudentEnrolment = new ArrayList<StudentEnrolment>();
    }
    public StudentEnrolmentList(ArrayList<StudentEnrolment> listStudentEnrolment) {
        this.listStudentEnrolment = listStudentEnrolment;
    }

    public ArrayList<StudentEnrolment> getListStudentEnrolment() {
        return listStudentEnrolment;
    }

    public void setListStudentEnrolment(ArrayList<StudentEnrolment> listStudentEnrolment) {
        this.listStudentEnrolment = listStudentEnrolment;
    }
}
