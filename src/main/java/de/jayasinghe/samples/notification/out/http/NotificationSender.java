package de.jayasinghe.samples.notification.out.http;

import de.jayasinghe.samples.notification.events.NotificationDeliveredTriggered;
import de.jayasinghe.samples.notification.events.NotificationPlacedTriggered;
import de.jayasinghe.samples.notification.events.NotificationShippedTriggered;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class NotificationSender {
	private static final Logger logger = LoggerFactory.getLogger(NotificationSender.class);
	private final RestTemplate restTemplate;
	private static final URI SMS_URI = URI.create("https://fancy-sms-service.net/send");

	public NotificationSender() {
		this.restTemplate = new RestTemplate();
	}

	@EventListener
	@Async
	public void on(NotificationPlacedTriggered notificationPlacedTriggered) {
		restTemplate.postForEntity(SMS_URI, notificationPlacedTriggered.getNotification(), String.class);
		logger.info("sent orderPlaced notification {}", notificationPlacedTriggered.getNotification());
	}

	@EventListener
	@Async
	public void on(NotificationShippedTriggered notificationShippedTriggered) {
		restTemplate.postForEntity(SMS_URI, notificationShippedTriggered.getNotification(), String.class);
		logger.info("sent orderShipped notification {}", notificationShippedTriggered.getNotification());
	}

	@EventListener
	@Async
	public void on(NotificationDeliveredTriggered notificationDeliveredTriggered) {
		restTemplate.postForEntity(SMS_URI, notificationDeliveredTriggered.getNotification(), String.class);
		logger.info("sent orderDelivered notification {}", notificationDeliveredTriggered.getNotification());
	}
}
