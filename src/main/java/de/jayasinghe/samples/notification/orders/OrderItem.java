package de.jayasinghe.samples.notification.orders;

import java.math.*;

public class OrderItem {
	private final BigDecimal price;
	private final String name;

	public OrderItem(BigDecimal price, String name) {
		this.price = price;
		this.name = name;
	}

	@Override
	public String toString() {
		return "OrderItems{" + "price=" + price + ", name='" + name + '\'' + '}';
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}
}
