package org.example;

import java.util.List;

public class GradeCalculate {
//    private final List<Course>  courses;
// 2단계 일급컬렉션으로 수정
    private final Courses courses;
    public GradeCalculate(List<Course> courses){
//        this.courses = courses;
        // List 형태로 받더라도 일급컬렉션에게 위임하는 형태로 수정
        this.courses = new Courses(courses);
    }

    public double calculateGrade(){
        // 이수학점 * 학점
/* **********************  2단계 : 일급컬렉션으로 변경하면서 이부분이 Courses 로 이동 ***********************/
//        double multiplyCreditAndGrade = 0;
//        // 2단계 : 일급컬렉션으로 구현하기
//        // 장점 : for 문을 일급컬렉션에게 위임할 수 있따.
//        for(Course course: courses){
////            multiplyCreditAndGrade += course.getCredit() * course.getGradeToNumber();
//            // 1단계 위임! : 변화에 유여한 코드
//            multiplyCreditAndGrade += course.multiplyCreditAndGrade();
//        }
//
//        //수강신청 총 이수학점
//        int totalCompletedCredit = courses.stream().mapToInt(Course::getCredit).sum();
/* **********************  2단계 : 일급컬렉션으로 변경하면서 이부분이 Courses 로 이동 ***********************/
        return courses.multiplyCreditAndGrade() / courses.totalCompletedCredit();
    }
}
