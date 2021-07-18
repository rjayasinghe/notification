package de.jayasinghe.samples.notification.out;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import de.jayasinghe.samples.notification.dispatch.statemachine.ShippedNotificationTriggered;
import de.jayasinghe.samples.notification.events.DeliveredNotificationTriggered;
import de.jayasinghe.samples.notification.events.PlacedNotificationTriggered;

@Component
public class NotificationSender {
	private static final Logger logger = LoggerFactory.getLogger(NotificationSender.class);

	@EventListener
	public void on(PlacedNotificationTriggered placedNotificationTriggered) {
		logger.info("send orderPlaced notification for order with ID {} to {}",
				placedNotificationTriggered.getOrder().getOrderId(),
				placedNotificationTriggered.getOrder().getCustomer().getMobileNumber());

	}

	@EventListener
	public void on(ShippedNotificationTriggered shippedNotificationTriggered) {
		logger.info("send orderShipped notification for order with ID {} to {}",
				shippedNotificationTriggered.getOrder().getOrderId(),
				shippedNotificationTriggered.getOrder().getCustomer().getMobileNumber());

	}

	@EventListener
	public void on(DeliveredNotificationTriggered deliveredNotificationTriggered) {
		logger.info("send orderDelivered notification for order with ID {} to {}",
				deliveredNotificationTriggered.getOrder().getOrderId(),
				deliveredNotificationTriggered.getOrder().getCustomer().getMobileNumber());

	}
}
