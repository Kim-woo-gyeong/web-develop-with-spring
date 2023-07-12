package org.example;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CustomerTest {
	/*
	 * 도메인 구성 : 손님, 메뉴판, 돈까스/냉면/만두 : 메뉴, 요리사, 요리
	 * */
	@Test
	@DisplayName("손님이 메뉴를 주문한다.")
	public void order() {
		Cooking cooking = new Cooking();
		Menu menu = new Menu(Arrays.asList(new MenuItem("만두", 4000)
										  ,new MenuItem("돈까스", 7000)));
		Customer customer = new Customer();

		Assertions.assertThatCode(() -> customer.order("만두",menu,cooking))
				.doesNotThrowAnyException();
	}
}
