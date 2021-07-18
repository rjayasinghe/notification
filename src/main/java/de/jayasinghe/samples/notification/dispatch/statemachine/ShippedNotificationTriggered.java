package de.jayasinghe.samples.notification.dispatch.statemachine;

import de.jayasinghe.samples.notification.Order;

public class ShippedNotificationTriggered {

	private final Order order;

	public ShippedNotificationTriggered(Order order) {
		this.order = order;
	}

	public Order getOrder() {
		return order;
	}

}
