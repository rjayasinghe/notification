package de.jayasinghe.samples.notification.events;

import de.jayasinghe.samples.notification.Order;

public class PlacedNotificationTriggered {
	private final Order order;

	public PlacedNotificationTriggered(Order order) {
		this.order = order;
	}

	public Order getOrder() {
		return order;
	}
}
