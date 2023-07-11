package org.example;


import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class MenuTest {
	@Test
	@DisplayName("메뉴판에서 메뉴이름에 해당하는 메뉴를 반환한다.")
	public void chooseTest() {
		Menu menu = new Menu(Arrays.asList(
								 new MenuItem("돈까스" , 5400)
								,new MenuItem("냉면" , 7000)));

		MenuItem menuItem = menu.choose("돈까스");
		Assertions.assertThat(menuItem).isEqualTo(new MenuItem("돈까스",5400));
	}

	@Test
	@DisplayName("메뉴판에 없는 메뉴를 고른 경우 예외처리 한다.")
	public void chooseTest2() {
		Menu menu = new Menu(Arrays.asList(
								 new MenuItem("돈까스" , 5400)
								,new MenuItem("냉면" , 7000)));

		Assertions.assertThatCode(() -> menu.choose("통닭"))
					.isInstanceOf(IllegalArgumentException.class)
					.hasMessage("잘못된 메뉴이름입니다.");

	}
}
