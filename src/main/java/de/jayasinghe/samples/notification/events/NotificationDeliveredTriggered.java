package de.jayasinghe.samples.notification.events;

import de.jayasinghe.samples.notification.Notification;

public class NotificationDeliveredTriggered implements NotificationEvent {
	private final Notification notification;

	public NotificationDeliveredTriggered(Notification notification) {
		this.notification = notification;
	}

	@Override
	public Notification getNotification() {
		return notification;
	}
}
