package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CookingTest {
	@Test
	@DisplayName("메뉴에 해당하는 음식을 만든다.")
	public void makeCookTest() {
		Cooking cooking = new Cooking();	// 요리사 생성
		MenuItem menuItem = new MenuItem("돈까스", 5000);	// 메뉴주문

		// 요리사에게 메뉴를 요리하라고 요청
		Cook cook = cooking.makeCook(menuItem);
		Assertions.assertThat(cook).isEqualTo(new Cook("돈까스", 5000));
	}
}
