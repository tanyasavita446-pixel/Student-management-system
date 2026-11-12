import java.util.*;
import java.io.*;

public class StudentManager implements StudentOperations {

    private ArrayList<Student> students = new ArrayList<>();
    private HashMap<Integer, Student> studentMap = new HashMap<>();
    private HashSet<Integer> studentIds = new HashSet<>();

    private final String FILE = "data/students.txt";

    public StudentManager() {
        loadData();
    }

    private void rebuildIndexes() {
        studentMap.clear();
        studentIds.clear();

        for (Student s : students) {
            studentMap.put(s.getStudentId(), s);
            studentIds.add(s.getStudentId());
        }
    }

    @Override
    public synchronized void addStudent(Student student) {

        if (studentIds.contains(student.getStudentId())) {
            System.out.println("Student ID already exists!");
            return;
        }

        students.add(student);
        studentMap.put(student.getStudentId(), student);
        studentIds.add(student.getStudentId());

        System.out.println("Student added successfully!");
        saveInBackground();
    }

    @Override
    public void viewAllStudents() {

        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        for (Student s : students) {
            System.out.println("--------------------");
            s.displayDetails();
        }
    }

    @Override
    public void searchStudent(int id) {

        Student s = studentMap.get(id);

        if (s != null) {
            s.displayDetails();
        } else {
            System.out.println("Student not found!");
        }
    }

    @Override
    public synchronized void updateStudent(int id, String course, double marks) {

        Student s = studentMap.get(id);

        if (s != null) {
            s.setCourse(course);
            s.setMarks(marks);

            System.out.println("Student updated successfully!");
            saveInBackground();
        } else {
            System.out.println("Student not found!");
        }
    }

    @Override
    public synchronized void deleteStudent(int id) {

        Student s = studentMap.get(id);

        if (s != null) {
            students.remove(s);
            rebuildIndexes();

            System.out.println("Student deleted successfully!");
            saveInBackground();
        } else {
            System.out.println("Student not found!");
        }
    }

    private synchronized void saveInBackground() {

        ArrayList<Student> snapshot = new ArrayList<>(students);

        Thread thread = new Thread(() -> saveData(snapshot));
        thread.start();
    }

    private void saveData(ArrayList<Student> records) {

        try {
            File folder = new File("data");
            folder.mkdirs();

            PrintWriter writer = new PrintWriter(new FileWriter(FILE));

            for (Student s : records) {
                writer.println(
                    s.getStudentId() + "|" +
                    s.getName() + "|" +
                    s.getCourse() + "|" +
                    s.getAge() + "|" +
                    s.getMarks()
                );
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public void saveBeforeExit() {
        ArrayList<Student> snapshot;

        synchronized (this) {
            snapshot = new ArrayList<>(students);
        }

        saveData(snapshot);
    }

    private void loadData() {

        File file = new File(FILE);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                Student s = new Student(
                    Integer.parseInt(data[0]),
                    data[1],
                    data[2],
                    Integer.parseInt(data[3]),
                    Double.parseDouble(data[4])
                );

                if (!studentIds.contains(s.getStudentId())) {
                    students.add(s);
                    studentMap.put(s.getStudentId(), s);
                    studentIds.add(s.getStudentId());
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}