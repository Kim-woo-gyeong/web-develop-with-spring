package calculator.org.example;

import calculator.org.example.calculate.PositiveNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class CalculateTest {
//    @Test
//    @DisplayName("덧셈 연산을 수행한다.")
//    void additionTest(){
//        int result = Calculate.calculate(1, "+", 2);
//        Assertions.assertThat(result).isEqualTo(3);
//    }

    @DisplayName("덧셈 연산을 수행한다-파라미터를 받아서 수행한다.")
    @ParameterizedTest
    @MethodSource("formulaAndResult")
    void calculateTest(int op1, String op, int op2, int result){
        int calculateResult = Calculate.calculate(new PositiveNumber(op1),op, new PositiveNumber(op2));
        Assertions.assertThat(calculateResult).isEqualTo(result);
    }

    private static Stream<Arguments> formulaAndResult(){
        return Stream.of(
                Arguments.arguments(1,"+",2,3),
                Arguments.arguments(1,"-",2,-1),
                Arguments.arguments(4,"*",2,8),
                Arguments.arguments(4,"/",2,2)
        );
    }

//    @Test
//    @DisplayName("나눗셈에서 0으로 나누는 경우 예외를 발생시킨다.")
//    void calculateExeptionTest(){
//        Assertions.assertThatCode(() -> Calculate.calculate(new PositiveNumber(10), "/", new PositiveNumber(0)))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("0으로 나눌 수 없습니다.");
//    }

}
