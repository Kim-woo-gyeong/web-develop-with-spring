package org.exmaple;

import org.assertj.core.api.Assertions;
import org.example.Course;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CourseTest {

    @DisplayName("과목을 생성합니다.")
    @Test
    public void createTest() {
        Assertions.assertThatCode(() -> new Course("OOP", 3, "A+"))
                .doesNotThrowAnyException();//
    }
}

