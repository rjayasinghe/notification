package de.jayasinghe.samples.notification.in.amqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMqConfig {

	private static final String ORDER_DELIVERED_ROUTING_KEY = "order.delivered.#";
	private static final String ORDER_SHIPPED_ROUTING_KEY = "order.shipped.#";
	private static final String ORDER_PLACED_ROUTING_KEY = "order.placed.#";

	private static final String ORDER_EXCHANGE_NAME = "orders";
	static final String ORDER_PLACED_QUEUE_NAME = "order.placed";
	static final String ORDER_SHIPPED_QUEUE_NAME = "order.shipped";
	static final String ORDER_DELIVERED_QUEUE_NAME = "order.delivered";


	@Bean
	Queue orderPlacedQueue() {
		return QueueBuilder.durable(ORDER_PLACED_QUEUE_NAME).build();
	}

	@Bean
	Queue orderShippedQueue() {
		return QueueBuilder.durable(ORDER_SHIPPED_QUEUE_NAME).build();
	}

	@Bean
	Queue orderDeliveredQueue() {
		return QueueBuilder.durable(ORDER_DELIVERED_QUEUE_NAME).build();
	}

	@Bean
	TopicExchange orderExchange() {
		return ExchangeBuilder.topicExchange(ORDER_EXCHANGE_NAME).build();
	}

	@Bean
	Binding orderPlacedBinding(Queue orderPlacedQueue, TopicExchange orderExchange) {
		return BindingBuilder.bind(orderPlacedQueue).to(orderExchange).with(ORDER_PLACED_ROUTING_KEY);
	}

	@Bean
	Binding orderShippedBinding(Queue orderShippedQueue, TopicExchange orderExchange) {
		return BindingBuilder.bind(orderShippedQueue).to(orderExchange).with(ORDER_SHIPPED_ROUTING_KEY);
	}

	@Bean
	Binding orderDeliveredBinding(Queue orderDeliveredQueue, TopicExchange orderExchange) {
		return BindingBuilder.bind(orderDeliveredQueue).to(orderExchange).with(ORDER_DELIVERED_ROUTING_KEY);
	}
}