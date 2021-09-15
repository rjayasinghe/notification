package de.jayasinghe.samples.notification.in.amqp;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import de.jayasinghe.samples.notification.Order;
import de.jayasinghe.samples.notification.OrderItem;

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

	public static class Pizza {
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

	public static class Customer {
		private final String firstName;
		private final String lastName;
		private final String email;
		private final String mobileNumber;

		public Customer(String firstName, String lastName, String email, String mobileNumber) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.mobileNumber = mobileNumber;
		}

		public String getFirstName() {
			return firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public String getEmail() {
			return email;
		}

		public String getMobileNumber() {
			return mobileNumber;
		}

		public de.jayasinghe.samples.notification.Customer toCustomer() {
			return new de.jayasinghe.samples.notification.Customer(firstName, lastName, email, mobileNumber);
		}
	}
}
