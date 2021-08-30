package de.jayasinghe.samples.notification;

import java.util.*;

public class Order {
	private final UUID orderId;
	private final Customer customer;
	private final List<OrderItem> orderItems;
	public Order(UUID orderId, Customer customer, List<OrderItem> orderItems) {
		this.orderId = orderId;
		this.customer = customer;
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "Order{" + "orderId=" + orderId + ", customer=" + customer + ", orderItems=" + orderItems + '}';
	}

	public UUID getOrderId() {
		return orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
}
