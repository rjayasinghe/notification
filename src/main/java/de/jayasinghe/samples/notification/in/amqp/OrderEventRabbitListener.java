package de.jayasinghe.samples.notification.in.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventRabbitListener {

	private static final Logger logger = LoggerFactory.getLogger(OrderEventRabbitListener.class);

	@RabbitListener(queues = {RabbitMqConfig.ORDER_PLACED_QUEUE_NAME})
    public void receivedOrderPlaced(String message) {
		logger.info("received order.placed: {}", message);
    }

	@RabbitListener(queues = {RabbitMqConfig.ORDER_SHIPPED_QUEUE_NAME})
    public void receivedOrderShipped(String message) {
		logger.info("received order.shipped: {}", message);
    }

	@RabbitListener(queues = {RabbitMqConfig.ORDER_DELIVERED_QUEUE_NAME})
    public void receivedOrderDelivered(String message) {
		logger.info("received order.delivered: {}", message);
    }
}
