package de.jayasinghe.samples.notification.in.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import de.jayasinghe.samples.notification.events.OrderDeliveredReceivedEvent;
import de.jayasinghe.samples.notification.events.OrderPlacedReceivedEvent;
import de.jayasinghe.samples.notification.events.OrderShippedReceivedEvent;

@Component
public class OrderEventRabbitListener {

	private static final Logger logger = LoggerFactory.getLogger(OrderEventRabbitListener.class);

	private final ApplicationEventPublisher applicationEventPublisher;

	public OrderEventRabbitListener(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@RabbitListener(queues = { RabbitMqConfig.ORDER_PLACED_QUEUE_NAME })
	public void receivedOrderPlaced(PizzaOrder pizzaOrder) {
		applicationEventPublisher.publishEvent(new OrderPlacedReceivedEvent(pizzaOrder.toOrder()));
		logger.info("received order.placed: {}", pizzaOrder);
	}

	@RabbitListener(queues = { RabbitMqConfig.ORDER_SHIPPED_QUEUE_NAME })
	public void receivedOrderShipped(PizzaOrder pizzaOrder) {
		applicationEventPublisher.publishEvent(new OrderShippedReceivedEvent(pizzaOrder.toOrder()));
		logger.info("received order.shipped: {}", pizzaOrder);
	}

	@RabbitListener(queues = { RabbitMqConfig.ORDER_DELIVERED_QUEUE_NAME })
	public void receivedOrderDelivered(PizzaOrder pizzaOrder) {
		applicationEventPublisher.publishEvent(new OrderDeliveredReceivedEvent(pizzaOrder.toOrder()));
		logger.info("received order.delivered: {}", pizzaOrder);
	}
}
