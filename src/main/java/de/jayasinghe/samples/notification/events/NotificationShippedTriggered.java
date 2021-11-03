package de.jayasinghe.samples.notification.events;

import de.jayasinghe.samples.notification.Notification;

public class NotificationShippedTriggered implements NotificationEvent {

	private final Notification notification;

	public NotificationShippedTriggered(Notification notification) {
		this.notification = notification;
	}

	@Override
	public Notification getNotification() {
		return notification;
	}
}
