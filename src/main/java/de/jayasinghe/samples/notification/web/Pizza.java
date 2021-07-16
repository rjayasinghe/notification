package de.jayasinghe.samples.notification.web;

import de.jayasinghe.samples.notification.orders.OrderItem;

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
