package de.jayasinghe.samples.notification.events;

import de.jayasinghe.samples.notification.Order;

public class OrderShippedReceivedEvent {
	private final Order incomingOrder;

	public OrderShippedReceivedEvent(Order incomingOrder) {
		this.incomingOrder = incomingOrder;
	}

	public Order getIncomingOrder() {
		return incomingOrder;
	}
}
