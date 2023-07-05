package calculator.org.example.calculate;

public class AdditionOperator implements newArithmeticOperator {

    @Override
    public boolean supports(String op) {
        return "+".equals(op);
    }

    @Override
    public int calculate(PositiveNumber op1, PositiveNumber op2) {
        return op1.toInt() + op2.toInt();
    }
}
