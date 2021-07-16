package de.jayasinghe.samples.notification.orders;

public class OrderShippedReceivedEvent {
	private final Order incomingOrder;

	public OrderShippedReceivedEvent(Order incomingOrder) {
		this.incomingOrder = incomingOrder;
	}

	public Order getIncomingOrder() {
		return incomingOrder;
	}
}
