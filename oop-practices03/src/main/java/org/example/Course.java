package org.example;

public class Course {
    private final String subject;   // 수업
    private final int credit;   // 이수학점
    private final String grade;   // 학점

    public Course(String subject, int credit, String grade) {
        this.subject = subject;
        this.credit = credit;
        this.grade = grade;
    }

    public double multiplyCreditAndGrade(){
        return getCredit() * getGradeToNumber();
    }
    public double getGradeToNumber(){
        double grade = 0;
        switch (this.grade){
            case "A+":
                grade = 4.5;
                break;
            case "A":
                grade = 4.0;
                break;
            case "B+":
                grade = 3.5;
                break;
            case "B":
                grade = 3.0;
                break;
            case "C+":
                grade = 2.5;
                break;
            case "C":
                grade = 2.0;
                break;
        }
        return grade;
    }

    public int getCredit(){
        return this.credit;
    }

}
