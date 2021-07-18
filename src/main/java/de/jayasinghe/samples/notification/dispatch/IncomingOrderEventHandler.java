package de.jayasinghe.samples.notification.dispatch;

import de.jayasinghe.samples.notification.dispatch.statemachine.OrderEvents;
import de.jayasinghe.samples.notification.dispatch.statemachine.OrderStates;
import de.jayasinghe.samples.notification.events.OrderDeliveredReceivedEvent;
import de.jayasinghe.samples.notification.events.OrderPlacedReceivedEvent;
import de.jayasinghe.samples.notification.events.OrderShippedReceivedEvent;

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

	@EventListener
	public void on(OrderDeliveredReceivedEvent orderDeliveredReceivedEvent) {
		StateMachine<OrderStates, OrderEvents> orderStateMachine = stateMachineService
				.acquireStateMachine(orderDeliveredReceivedEvent.getIncomingOrder().getOrderId().toString());
		Message<OrderEvents> message = MessageBuilder.withPayload(OrderEvents.DELIVERED)
				.setHeader("order", orderDeliveredReceivedEvent.getIncomingOrder()).build();
		orderStateMachine.sendEvent(message);
	}
}
