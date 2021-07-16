package de.jayasinghe.samples.notification.orders.dispatch.statemachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import de.jayasinghe.samples.notification.orders.Order;

@Component
public class PlacedAction implements Action<OrderStates, OrderEvents> {
	private static final Logger logger = LoggerFactory.getLogger(PlacedAction.class);

	@Override
	public void execute(StateContext<OrderStates, OrderEvents> context) {

		logger.info("order {} was placed. state machine ID {}",
				((Order) context.getMessageHeader("order")),
				context.getStateMachine().getId());
	}

}
