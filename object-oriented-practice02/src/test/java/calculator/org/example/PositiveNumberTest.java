package calculator.org.example;

import calculator.org.example.calculate.PositiveNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PositiveNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {-1})
    void createTest(int value){
        Assertions.assertThatCode(() -> new PositiveNumber(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("오류발생");
    }
}
