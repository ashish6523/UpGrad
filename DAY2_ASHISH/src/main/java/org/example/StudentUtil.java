package org.example;

import java.util.ArrayList;
import java.util.List;

public class StudentUtil {

    public static double[] calculateGPA(int[] studentIdList, char[][] studentsGrades) {
        double[] gpaArray = new double[studentIdList.length];

        for (int i = 0; i < studentIdList.length; i++) {
            int totalPoints = 0;
            int totalCourses = studentsGrades[i].length;

            for (char grade : studentsGrades[i]) {
                switch (grade) {
                    case 'A':
                        totalPoints += 4;
                        break;
                    case 'B':
                        totalPoints += 3;
                        break;
                    case 'C':
                        totalPoints += 2;
                        break;
                    default:
                        // Ignore unknown grades
                        break;
                }
            }

            gpaArray[i] = (double) totalPoints / totalCourses;
        }

        return gpaArray;
    }

    public static int[] getStudentsByGPA(double lower, double higher, int[] studentIdList, char[][] studentsGrades) {
        if (lower < 0 || higher < 0 || lower > higher) {
            return null;
        }

        List<Integer> result = new ArrayList<>();
        double[] gpaArray = calculateGPA(studentIdList, studentsGrades);

        for (int i = 0; i < gpaArray.length; i++) {
            if (gpaArray[i] >= lower && gpaArray[i] <= higher) {
                result.add(studentIdList[i]);
            }
        }

        int[] resultArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultArray[i] = result.get(i);
        }

        return resultArray;
    }
}

