import student.Student;

public class PackageMain {

    public static void main(String[] args) {

        Student student = new Student(
            101,
            "Sampath",
            "Computer Science and Engineering"
        );

        student.displayDetails();
    }
}
