package org.example;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuItemTest {
	@Test
	@DisplayName("메뉴를 생성한다.")
	void createTest() {
		Assertions.assertThatCode(() -> new MenuItem("만두", 5400))
			.doesNotThrowAnyException();;
	}
}
