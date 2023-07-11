package org.example;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class CookTest {

	@Test
	@DisplayName("요리를 생성한다.")
	public void createTest() {
		Assertions.assertThatCode(() -> new Cook("만두", 5400))
				 .doesNotThrowAnyException();
	}
}
