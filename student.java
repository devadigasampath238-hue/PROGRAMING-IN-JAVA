import java.util.ArrayList;
import java.util.Scanner;

class Student {
    int studentId;
    String studentName;
    String department;
    int semester;

    public Student(int studentId, String studentName,
                   String department, int semester) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.department = department;
        this.semester = semester;
    }

    public void displayDetails() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Student Name: " + studentName);
        System.out.println("Department: " + department);
        System.out.println("Semester: " + semester);
        System.out.println("-------------------------");
    }
}

public class StudentRecordManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        int choice;

        do {
            System.out.println("\nSTUDENT RECORD MANAGEMENT");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    boolean exists = false;

                    for (Student s : students) {
                        if (s.studentId == id) {
                            exists = true;
                            break;
                        }
                    }

                    if (exists) {
                        System.out.println("Student ID already exists.");
                        break;
                    }

                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Department: ");
                    String department = sc.nextLine();

                    System.out.print("Enter Semester: ");
                    int semester = sc.nextInt();
                    sc.nextLine();

                    students.add(new Student(id, name, department, semester));

                    System.out.println("Student record added successfully.");
                    break;

                case 2:
                    System.out.print("Enter Student ID to remove: ");
                    int removeId = sc.nextInt();
                    sc.nextLine();

                    boolean removed = false;

                    for (int i = 0; i < students.size(); i++) {
                        if (students.get(i).studentId == removeId) {
                            students.remove(i);
                            removed = true;
                            break;
                        }
                    }

                    if (removed) {
                        System.out.println("Student record removed successfully.");
                    } else {
                        System.out.println("Student record not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Student ID to search: ");
                    int searchId = sc.nextInt();
                    sc.nextLine();

                    boolean found = false;

                    for (Student s : students) {
                        if (s.studentId == searchId) {
                            s.displayDetails();
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Student record not found.");
                    }
                    break;

                case 4:
                    if (students.isEmpty()) {
                        System.out.println("No student records available.");
                    } else {
                        for (Student s : students) {
                            s.displayDetails();
                        }
                    }
                    break;

                case 5:
                    System.out.println("Exiting Student Record Management.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}
