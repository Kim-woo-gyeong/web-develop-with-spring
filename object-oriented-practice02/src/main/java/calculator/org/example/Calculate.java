package calculator.org.example;

import calculator.org.example.calculate.*;

import java.util.List;

//
public class Calculate {
    private static final List<newArithmeticOperator> arithmeticOperator
            = List.of(new AdditionOperator(), new SubstractionOperator(), new MultiplicationOperator(), new DivisionOperator());
    public static int calculate(PositiveNumber op1, String op, PositiveNumber op2){
        // ArithmeticOperator 라는 enum 을 만들어서 재위임한다.
        //return ArithmeticOperator.calculate(op1, op, op2);

        // 리팩토링
        // 하나의 인터페이스를 두고
        // op 당 구현체를 찾는 식으로 구현.
        return arithmeticOperator.stream()
                .filter(arithmeticOperator -> arithmeticOperator.supports(op))
                .map(arithmeticOperator -> arithmeticOperator.calculate(op1, op2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산이 아닙니다."));
    }
}
