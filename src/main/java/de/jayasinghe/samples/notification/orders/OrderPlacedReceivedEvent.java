package de.jayasinghe.samples.notification.orders;

public class OrderPlacedReceivedEvent {
	private final Order incomingOrder;

	public OrderPlacedReceivedEvent(Order incomingOrder) {
		this.incomingOrder = incomingOrder;
	}

	public Order getIncomingOrder() {
		return incomingOrder;
	}
}