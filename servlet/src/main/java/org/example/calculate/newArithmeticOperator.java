package org.example.calculate;

public interface newArithmeticOperator {
    boolean supports(String op);    // op 를 지원하는지
    int calculate(PositiveNumber op1, PositiveNumber op2);
}
