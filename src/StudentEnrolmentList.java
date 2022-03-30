import java.util.ArrayList;
public class StudentEnrolmentList {
    private ArrayList<StudentEnrolment> listStudentEnrolment = new ArrayList<StudentEnrolment>();

    public StudentEnrolmentList(ArrayList<StudentEnrolment> listStudentEnrolment) {
        this.listStudentEnrolment = listStudentEnrolment;
    }

    public ArrayList<StudentEnrolment> getListStudentdentEnrolment() {
        return listStudentEnrolment;
    }

    public void setListStudentdentEnrolment(ArrayList<StudentEnrolment> listStudentEnrolment) {
        this.listStudentEnrolment = listStudentEnrolment;
    }
}
