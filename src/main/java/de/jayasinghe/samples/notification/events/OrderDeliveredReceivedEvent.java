package de.jayasinghe.samples.notification.events;

import de.jayasinghe.samples.notification.Order;

public class OrderDeliveredReceivedEvent {
	private final Order incomingOrder;

	public OrderDeliveredReceivedEvent(Order incomingOrder) {
		this.incomingOrder = incomingOrder;
	}

	public Order getIncomingOrder() {
		return incomingOrder;
	}
}
