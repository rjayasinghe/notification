package de.jayasinghe.samples.notification.events;

import de.jayasinghe.samples.notification.Notification;

public class NotificationDeliveredTriggered {
	private final Notification notification;

	public NotificationDeliveredTriggered(Notification notification) {
		this.notification = notification;
	}

	public Notification getNotification() {
		return notification;
	}
}
