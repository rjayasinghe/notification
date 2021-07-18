package de.jayasinghe.samples.notification.in.http;

import de.jayasinghe.samples.notification.OrderItem;

import java.math.BigDecimal;

public class Pizza {
	final String name;
	final BigDecimal price;

	public Pizza(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public OrderItem toOrderItem() {
		return new OrderItem(price, name);
	}
}
