package de.jayasinghe.samples.notification.dispatch.statemachine;

import de.jayasinghe.samples.notification.Order;
import static de.jayasinghe.samples.notification.dispatch.OrderToNotificationConverter.convertForShippedOrder;
import de.jayasinghe.samples.notification.events.NotificationShippedTriggered;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Component
public class ShippedAction implements Action<OrderStates, OrderEvents> {
	private static final Logger logger = LoggerFactory.getLogger(ShippedAction.class);

	private final ApplicationEventPublisher applicationEventPublisher;

	public ShippedAction(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public void execute(StateContext<OrderStates, OrderEvents> context) {
		Order order = (Order) context.getMessageHeader("order");
		applicationEventPublisher.publishEvent(
				new NotificationShippedTriggered(convertForShippedOrder(order)));

		logger.info("dispatched ShippedNotificationTriggered event for order with orderId {}", order.getOrderId());
	}

}
