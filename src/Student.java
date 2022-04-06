import java.util.Date;
public class Student {
    private String studentId;
    private String name;
    private String birthdate;

    public Student(String studentId, String name, String birthdate) {
        this.studentId = studentId;
        this.name = name;
        this.birthdate = birthdate;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
    public String toString(){
        return "student id is "+studentId+", student name is "+name+" with "+birthdate+" is the date of birth.";

    }
}
