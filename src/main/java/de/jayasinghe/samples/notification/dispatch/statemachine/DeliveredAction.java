package de.jayasinghe.samples.notification.dispatch.statemachine;

import de.jayasinghe.samples.notification.Order;
import static de.jayasinghe.samples.notification.dispatch.OrderToNotificationConverter.convertForDelivered;
import de.jayasinghe.samples.notification.events.NotificationDeliveredTriggered;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Component
public class DeliveredAction implements Action<OrderStates, OrderEvents> {
	private static final Logger logger = LoggerFactory.getLogger(DeliveredAction.class);
	private final ApplicationEventPublisher applicationEventPublisher;

	public DeliveredAction(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public void execute(StateContext<OrderStates, OrderEvents> context) {
		Order order = ((Order) context.getMessageHeader("order"));
		this.applicationEventPublisher.publishEvent(new NotificationDeliveredTriggered(convertForDelivered(order)));

		logger.info("dispatched NotificationDeliveredTriggered event for order with orderId {}", order.getOrderId());
	}
}
