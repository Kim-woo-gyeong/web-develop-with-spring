package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class QueryStringTest {
	@Test
	void createTest() {
		QueryString queryString = new QueryString("operator","11");
		Assertions.assertThat(queryString).isNotNull();
	}
}
