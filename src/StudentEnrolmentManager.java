public interface StudentEnrolmentManager {
    public boolean add(StudentEnrolment studentEnrolment);
    public boolean update(String courseId,String studentId,String semester ,StudentEnrolment updatedStudentEnrolment);
    public boolean delete(String courseId,String studentId,String semester);
    public StudentEnrolment getOne(String courseId,String studentId,String semester);
    public StudentEnrolmentList getAll();
}
