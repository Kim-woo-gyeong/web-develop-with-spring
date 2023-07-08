package org.exmaple;

import java.util.*;

import org.assertj.core.api.Assertions;
import org.example.Course;
import org.example.GradeCalculate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GradeCalculateTest {

    @DisplayName("평균학점을 계산한다.")
    @Test
    public void calculateGradeTest() {
        List<Course> courses = List.of(new Course("OOP",3,"A+"),
                new Course("자료구조", 2, "A+"));

        GradeCalculate gradeCalculate = new GradeCalculate(courses);
        double gradeResult = gradeCalculate.calculateGrade();   // 학점을 계산한다.

        Assertions.assertThat(gradeResult).isEqualTo(4.5);


    }
}
