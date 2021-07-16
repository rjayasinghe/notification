package de.jayasinghe.samples.notification.orders.dispatch;

import de.jayasinghe.samples.notification.orders.OrderPlacedReceivedEvent;
import de.jayasinghe.samples.notification.orders.OrderShippedReceivedEvent;
import de.jayasinghe.samples.notification.orders.dispatch.statemachine.OrderEvents;
import de.jayasinghe.samples.notification.orders.dispatch.statemachine.OrderStates;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Component;

@Component
public class IncomingOrderEventHandler {
	private final StateMachineFactory<OrderStates, OrderEvents> stateMachineFactory;

	public IncomingOrderEventHandler(StateMachineFactory<OrderStates, OrderEvents> stateMachineFactory) {
		this.stateMachineFactory = stateMachineFactory;
	}

	@EventListener
	public void on(OrderPlacedReceivedEvent orderPlacedReceivedEvent) {
		StateMachine<OrderStates, OrderEvents> orderStateMachine = stateMachineFactory
				.getStateMachine(orderPlacedReceivedEvent.getIncomingOrder().getOrderId());
		orderStateMachine.start();
		Message<OrderEvents> message = MessageBuilder.withPayload(OrderEvents.PLACED)
				.setHeader("order", orderPlacedReceivedEvent.getIncomingOrder()).build();
		orderStateMachine.sendEvent(message);
	}

	@EventListener
	public void on(OrderShippedReceivedEvent orderShippedReceivedEvent) {
		StateMachine<OrderStates, OrderEvents> orderStateMachine = stateMachineFactory
				.getStateMachine(orderShippedReceivedEvent.getIncomingOrder().getOrderId());
		orderStateMachine.start();
		Message<OrderEvents> message = MessageBuilder.withPayload(OrderEvents.SHIPPED)
				.setHeader("order", orderShippedReceivedEvent.getIncomingOrder()).build();
		orderStateMachine.sendEvent(message);
	}
}
