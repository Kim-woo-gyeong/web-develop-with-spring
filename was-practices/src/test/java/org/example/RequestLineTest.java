package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RequestLineTest {
    @Test
    void createTest(){
        RequestLine requestLine = new RequestLine("GET /calculate?operator1=11&operator=+&operator2=55");
        Assertions.assertThat(requestLine).isNotNull();

        Assertions.assertThat(requestLine).isEqualTo(new RequestLine("GET","/calculate","operator1=11&operator=+&operator2=55"));
    }
}
