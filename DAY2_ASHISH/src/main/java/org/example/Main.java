package org.example;
import org.example.StudentUtil;

public class Main {
    public static void main(String[] args) {

        int[] studentIdList = {1001, 1002};
        char[][] studentsGrades = {{'A', 'A', 'A', 'B'}, {'A', 'B', 'B'}};

        // Calculate GPAs
        double[] gpas = StudentUtil.calculateGPA(studentIdList, studentsGrades);
        System.out.println("GPAs:");
        for (int i = 0; i < gpas.length; i++) {
            System.out.println("Student ID: " + studentIdList[i] + ", GPA: " + gpas[i]);
        }

        // Get students by GPA range
        double lower = 3.2;
        double higher = 3.5;
        int[] selectedStudents = StudentUtil.getStudentsByGPA(lower, higher, studentIdList, studentsGrades);
        System.out.println("\nStudents within GPA range [" + lower + ", " + higher + "]:");
        for (int student : selectedStudents) {
            System.out.println("Student ID: " + student);
        }
    }
}