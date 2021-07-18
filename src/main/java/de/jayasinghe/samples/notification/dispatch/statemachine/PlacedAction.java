package de.jayasinghe.samples.notification.dispatch.statemachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import de.jayasinghe.samples.notification.Order;
import de.jayasinghe.samples.notification.events.PlacedNotificationTriggered;

@Component
public class PlacedAction implements Action<OrderStates, OrderEvents> {
	private static final Logger logger = LoggerFactory.getLogger(PlacedAction.class);
	private final ApplicationEventPublisher applicationEventPublisher;

	public PlacedAction(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public void execute(StateContext<OrderStates, OrderEvents> context) {
		Order order = (Order) context.getMessageHeader("order");
		applicationEventPublisher.publishEvent(new PlacedNotificationTriggered(order));

		logger.info("order {} was placed. state machine ID {}", order,
				context.getStateMachine().getId());
	}

}
