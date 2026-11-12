import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        int choice;

        do {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:

                        System.out.print("Enter Student ID: ");
                        int id = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Course: ");
                        String course = sc.nextLine();

                        System.out.print("Enter Age: ");
                        int age = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter Marks: ");
                        double marks = Double.parseDouble(sc.nextLine());

                        if (age <= 0 || marks < 0 || marks > 100) {
                            System.out.println("Invalid age or marks!");
                            break;
                        }

                        Student s = new Student(id, name, course, age, marks);

                        manager.addStudent(s);
                        break;

                    case 2:
                        manager.viewAllStudents();
                        break;

                    case 3:

                        System.out.print("Enter Student ID: ");
                        id = Integer.parseInt(sc.nextLine());

                        manager.searchStudent(id);
                        break;

                    case 4:

                        System.out.print("Enter Student ID: ");
                        id = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter New Course: ");
                        course = sc.nextLine();

                        System.out.print("Enter New Marks: ");
                        marks = Double.parseDouble(sc.nextLine());

                        if (marks < 0 || marks > 100) {
                            System.out.println("Invalid marks!");
                            break;
                        }

                        manager.updateStudent(id, course, marks);
                        break;

                    case 5:

                        System.out.print("Enter Student ID: ");
                        id = Integer.parseInt(sc.nextLine());

                        manager.deleteStudent(id);
                        break;

                    case 6:

                        manager.saveBeforeExit();
                        System.out.println("Thank you! Application closed.");
                        break;

                    default:
                        System.out.println("Invalid choice!");

                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter valid numeric input!");
                choice = 0;
            }

        } while (choice != 6);

        sc.close();
    }
}