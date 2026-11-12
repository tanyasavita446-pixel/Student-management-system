public class Student extends Person {

    private int studentId;
    private String course;
    private double marks;

    public Student(int studentId, String name, String course, int age, double marks) {
        super(name, age);
        this.studentId = studentId;
        this.course = course;
        this.marks = marks;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getCourse() {
        return course;
    }

    public double getMarks() {
        return marks;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public void displayDetails() {
        System.out.println("Student ID: " + studentId);
        super.displayDetails();
        System.out.println("Course: " + course);
        System.out.println("Marks: " + marks);
    }
}