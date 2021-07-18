package de.jayasinghe.samples.notification.dispatch.statemachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import de.jayasinghe.samples.notification.Order;
import de.jayasinghe.samples.notification.events.DeliveredNotificationTriggered;

@Component
public class DeliveredAction implements Action<OrderStates, OrderEvents> {
	private final static Logger logger = LoggerFactory.getLogger(DeliveredAction.class);
	private final ApplicationEventPublisher applicationEventPublisher;

	public DeliveredAction(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public void execute(StateContext<OrderStates, OrderEvents> context) {
		Order order = ((Order) context.getMessageHeader("order"));
		this.applicationEventPublisher.publishEvent(new DeliveredNotificationTriggered(order));

		logger.info("order {} was delivered. state machine ID {}", order, context.getStateMachine().getId());

	}

}
