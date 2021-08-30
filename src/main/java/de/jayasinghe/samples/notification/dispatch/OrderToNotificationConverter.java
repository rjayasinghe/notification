package de.jayasinghe.samples.notification.dispatch;

import de.jayasinghe.samples.notification.Notification;
import de.jayasinghe.samples.notification.Order;

public class OrderToNotificationConverter {
	private OrderToNotificationConverter() {
		// no instances here
	}

	public static Notification convertForPlacedOrder(Order order) {
		throw new UnsupportedOperationException("tbd");
	}

	public static Notification convertForShippedOrder(Order order) {
		throw new UnsupportedOperationException("tbd");
	}

	public static Notification convertForDelivered(Order order) {
		throw new UnsupportedOperationException("tbd");
	}
}
