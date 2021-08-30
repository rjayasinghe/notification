package de.jayasinghe.samples.notification.events;

import de.jayasinghe.samples.notification.Notification;

public class NotificationShippedTriggered {

	private final Notification notification;

	public NotificationShippedTriggered(Notification notification) {
		this.notification = notification;
	}

	public Notification getNotification() {
		return notification;
	}
}
