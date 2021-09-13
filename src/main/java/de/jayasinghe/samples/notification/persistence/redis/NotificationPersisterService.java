package de.jayasinghe.samples.notification.persistence.redis;

import static de.jayasinghe.samples.notification.persistence.redis.Notification.fromDomainObject;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import de.jayasinghe.samples.notification.events.NotificationEvent;

@Service
public class NotificationPersisterService {

	private final NotificationRepository notificationRepository;

	public NotificationPersisterService(NotificationRepository notificationRepository) {
		this.notificationRepository = notificationRepository;
	}

	@EventListener
	public void consumeNotificationEvent(final NotificationEvent notificationEvent) {
		notificationRepository.save(fromDomainObject(notificationEvent.getNotification()));
	}
}
