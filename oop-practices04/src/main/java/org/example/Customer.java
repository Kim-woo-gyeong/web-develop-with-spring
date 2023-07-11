package org.example;

public class Customer {
	public void order(String name, Menu menu, Cooking cooking) {
		MenuItem menuItem = menu.choose(name);	// 요리가 있니?
		cooking.makeCook(menuItem);				// 요리 요청
	}
}
