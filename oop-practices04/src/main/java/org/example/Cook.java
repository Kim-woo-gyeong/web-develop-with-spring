package org.example;

public class Cook {
	private final String name;
	private final int price;
	Cook(String name, int price){
		this.name = name;
		this.price = price;
	}
	public Cook(MenuItem menuItem) {
		this.name = menuItem.getName();
		this.price = menuItem.getPrice();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + price;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cook other = (Cook) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price != other.price)
			return false;
		return true;
	}


}
