package de.jayasinghe.samples.notification.events;

import de.jayasinghe.samples.notification.Notification;

public class NotificationPlacedTriggered implements NotificationEvent {
	private final Notification notification;

	public NotificationPlacedTriggered(Notification notification) {
		this.notification = notification;
	}

	@Override
	public Notification getNotification() {
		return notification;
	}
}
