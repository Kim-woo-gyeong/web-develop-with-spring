package org.example;

import java.util.List;

public class Menu {
	private final List<MenuItem> menuItems;

	Menu(List<MenuItem> menuItems){
		this.menuItems = menuItems;
	}

	public MenuItem choose(String name) {
		return this.menuItems.stream()
							.filter(menuItem -> menuItem.matches(name))
							.findFirst()
							.orElseThrow(() -> new IllegalArgumentException());
	}
}
