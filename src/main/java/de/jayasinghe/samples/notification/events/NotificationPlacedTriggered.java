package de.jayasinghe.samples.notification.events;

import de.jayasinghe.samples.notification.Notification;

public class NotificationPlacedTriggered {
	private final Notification notification;

	public NotificationPlacedTriggered(Notification notification) {
		this.notification = notification;
	}

	public Notification getNotification() {
		return notification;
	}
}
