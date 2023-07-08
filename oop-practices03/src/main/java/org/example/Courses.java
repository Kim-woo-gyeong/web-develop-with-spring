package org.example;

import java.util.List;

public class Courses {
    private final List<Course> courses;

    Courses(List<Course> courses){
        this.courses = courses;
    }

    public double multiplyCreditAndGrade(){
        // 이수학점 * 학점
        double multiplyCreditAndGrade = 0;
        // 2단계 : 일급컬렉션으로 구현하기
        // 장점 : for 문을 일급컬렉션에게 위임할 수 있따.
        for(Course course: courses){
//            multiplyCreditAndGrade += course.getCredit() * course.getGradeToNumber();
            // 1단계 위임! : 변화에 유여한 코드
            multiplyCreditAndGrade += course.multiplyCreditAndGrade();
        }

        return multiplyCreditAndGrade;
    }

    public int totalCompletedCredit(){
        return courses.stream().mapToInt(Course::getCredit).sum();
    }
}
