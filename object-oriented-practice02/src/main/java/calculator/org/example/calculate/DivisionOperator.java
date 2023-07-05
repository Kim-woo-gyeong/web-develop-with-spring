package calculator.org.example.calculate;

public class DivisionOperator implements newArithmeticOperator {

    @Override
    public boolean supports(String op) {
        return "/".equals(op);
    }

    @Override
    public int calculate(PositiveNumber op1, PositiveNumber op2) {
        if(op2.toInt() == 0){
            throw new IllegalArgumentException("0으로는 나눌 수 없습니다.");
        }
        return op1 .toInt()/ op2.toInt();
    }
}
