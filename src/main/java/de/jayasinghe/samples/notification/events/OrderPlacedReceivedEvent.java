package de.jayasinghe.samples.notification.events;

import de.jayasinghe.samples.notification.Order;

public class OrderPlacedReceivedEvent {
	private final Order incomingOrder;

	public OrderPlacedReceivedEvent(Order incomingOrder) {
		this.incomingOrder = incomingOrder;
	}

	public Order getIncomingOrder() {
		return incomingOrder;
	}
}