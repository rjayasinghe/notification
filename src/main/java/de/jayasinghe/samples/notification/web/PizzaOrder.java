package de.jayasinghe.samples.notification.web;

import de.jayasinghe.samples.notification.orders.Order;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PizzaOrder {
	final UUID orderId;
	final List<Pizza> orderedPizzas;
	final Customer customer;

	public PizzaOrder(UUID orderId, List<Pizza> orderedPizzas, Customer customer) {
		this.orderId = orderId;
		this.orderedPizzas = orderedPizzas;
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public UUID getOrderId() {
		return orderId;
	}

	public List<Pizza> getOrderedPizzas() {
		return orderedPizzas;
	}

	public Order toOrder() {
		return new Order(orderId, customer.toCustomer(),
				orderedPizzas.stream().map(Pizza::toOrderItem).collect(Collectors.toList()));
	}
}
