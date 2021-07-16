package de.jayasinghe.samples.notification.orders.dispatch;

import de.jayasinghe.samples.notification.orders.OrderPlacedReceivedEvent;
import de.jayasinghe.samples.notification.orders.OrderShippedReceivedEvent;
import de.jayasinghe.samples.notification.orders.dispatch.statemachine.OrderEvents;
import de.jayasinghe.samples.notification.orders.dispatch.statemachine.OrderStates;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.stereotype.Component;

@Component
public class IncomingOrderEventHandler {
	private final StateMachineService<OrderStates, OrderEvents> stateMachineService;

	public IncomingOrderEventHandler(StateMachineService<OrderStates, OrderEvents> stateMachineFactory) {
		this.stateMachineService = stateMachineFactory;
	}

	@EventListener
	public void on(OrderPlacedReceivedEvent orderPlacedReceivedEvent) {
		StateMachine<OrderStates, OrderEvents> orderStateMachine = stateMachineService
				.acquireStateMachine(orderPlacedReceivedEvent.getIncomingOrder().getOrderId().toString(), true);
		Message<OrderEvents> message = MessageBuilder.withPayload(OrderEvents.PLACED)
				.setHeader("order", orderPlacedReceivedEvent.getIncomingOrder()).build();
		orderStateMachine.sendEvent(message);
	}

	@EventListener
	public void on(OrderShippedReceivedEvent orderShippedReceivedEvent) {
		StateMachine<OrderStates, OrderEvents> orderStateMachine = stateMachineService
				.acquireStateMachine(orderShippedReceivedEvent.getIncomingOrder().getOrderId().toString());
		Message<OrderEvents> message = MessageBuilder.withPayload(OrderEvents.SHIPPED)
				.setHeader("order", orderShippedReceivedEvent.getIncomingOrder()).build();
		orderStateMachine.sendEvent(message);
	}
}
