import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Student {
    String name;
    ArrayList<Integer> grades;

    Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    void addGrade(int grade) {
        grades.add(grade);
    }

    double getAverage() {
        if (grades.isEmpty()) return 0;
        int sum = 0;
        for (int g : grades) sum += g;
        return (double) sum / grades.size();
    }

    int getHighest() {
        return grades.stream().mapToInt(Integer::intValue).max().orElse(0);
    }

    int getLowest() {
        return grades.stream().mapToInt(Integer::intValue).min().orElse(0);
    }

    void printSummary() {
        System.out.println("\n-------------------------------");
        System.out.println("📘 Summary for: " + name);
        System.out.println("Grades: " + grades);
        System.out.printf("Average Grade : %.2f\n", getAverage());
        System.out.println("Highest Grade : " + getHighest());
        System.out.println("Lowest Grade  : " + getLowest());
        System.out.println("-------------------------------");
    }
}

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        int totalGrades = 0;

        System.out.println("========================================");
        System.out.println("🎓 STUDENT GRADE TRACKER - CodeAlpha Task");
        System.out.println("========================================");

        System.out.print("Enter number of students: ");
        int count;
        while (true) {
            try {
                count = scanner.nextInt();
                if (count <= 0) throw new Exception();
                break;
            } catch (Exception e) {
                System.out.print("❗ Please enter a valid positive number: ");
                scanner.nextLine();
            }
        }
        scanner.nextLine(); // consume newline

        for (int i = 1; i <= count; i++) {
            System.out.printf("\n🔹 Enter details for student %d:\n", i);

            System.out.print("  Name: ");
            String name = scanner.nextLine();

            int n;
            while (true) {
                System.out.print("  Number of grades: ");
                try {
                    n = scanner.nextInt();
                    if (n <= 0) throw new Exception();
                    break;
                } catch (Exception e) {
                    System.out.print("  ❗ Enter a valid number greater than 0.\n");
                    scanner.nextLine();
                }
            }

            Student s = new Student(name);

            for (int j = 1; j <= n; j++) {
                int g;
                while (true) {
                    System.out.printf("    Enter grade %d: ", j);
                    try {
                        g = scanner.nextInt();
                        if (g < 0 || g > 100) throw new Exception();
                        break;
                    } catch (Exception e) {
                        System.out.print("    ❗ Grade must be between 0 and 100.\n");
                        scanner.nextLine();
                    }
                }
                s.addGrade(g);
                totalGrades++;
            }

            scanner.nextLine(); // consume newline
            students.add(s);
        }

        // Print summary
        System.out.println("\n✅ All data entered successfully.");
        System.out.println("📊 Generating summary report...\n");

        for (Student s : students) {
            s.printSummary();
        }

        // Global stats
        System.out.println("========================================");
        System.out.println("📈 OVERALL STATISTICS");
        System.out.println("Total Students : " + students.size());
        System.out.println("Total Grades   : " + totalGrades);
        System.out.println("========================================");

        System.out.println("\n🎯 Report generation complete. Thank you!");
        scanner.close();
    }
}
