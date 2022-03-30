public class Course {
    private String classId;
    private String name;
    private int numOfCredits;

    public Course(String classId, String name, int numOfCredits) {
        this.classId = classId;
        this.name = name;
        this.numOfCredits = numOfCredits;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfCredits() {
        return numOfCredits;
    }

    public void setNumOfCredits(int numOfCredits) {
        this.numOfCredits = numOfCredits;
    }
    public String toString(){
        return "class id is "+classId+", class name is "+name+" with "+numOfCredits+" credits. \n";

    }
}
