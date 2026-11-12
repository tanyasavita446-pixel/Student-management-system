public interface StudentOperations {

    void addStudent(Student student);

    void viewAllStudents();

    void searchStudent(int id);

    void updateStudent(int id, String course, double marks);

    void deleteStudent(int id);
}