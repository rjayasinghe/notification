package de.jayasinghe.samples.notification.events;

import de.jayasinghe.samples.notification.Order;

public class DeliveredNotificationTriggered {
	private final Order order;

	public DeliveredNotificationTriggered(Order order) {
		this.order = order;
	}

	public Order getOrder() {
		return order;
	}

}
