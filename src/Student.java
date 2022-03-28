import java.util.Date;
public class Student {
    private String studentId;
    private String name;
    private Date birthdate;

    public Student(String studentId, String name, Date birthdate) {
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
