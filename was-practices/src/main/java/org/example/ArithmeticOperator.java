package org.example;

import java.util.Arrays;

public enum ArithmeticOperator {
     ADDITION("+") {
         @Override
         public int arithmeticCalculate(int op1, int op2) {
             return op1 + op2;
         }
     },SUBSTRACTION("-") {
        @Override
        public int arithmeticCalculate(int op1, int op2) {
            return op1 - op2;
        }
    },MULTIPLICATION("*") {
        @Override
        public int arithmeticCalculate(int op1, int op2) {
            return op1 * op2;
        }
    },DIVISION("/") {
        @Override
        public int arithmeticCalculate(int op1, int op2) {
            return op1 / op2;
        }
    };

    private final String operator;

    ArithmeticOperator(String operator) {
        this.operator = operator;
    }
    
    public abstract int arithmeticCalculate(final int op1, final int op2);
    
    // 외부에 노출되는 public method
    public static int calculate(int op1, String op, int op2){
        // Todo : values() 란?? -> 모든 enum 값을 가져온다.
        // 이런 생각을 할 수 있을까??
        ArithmeticOperator arithmeticOperator = Arrays.stream(values())
                .filter(v -> v.operator.equals(op))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산이 아닙니다."));

        return arithmeticOperator.arithmeticCalculate(op1, op2);
    }
    
}
