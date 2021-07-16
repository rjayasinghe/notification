package de.jayasinghe.samples.notification.web;

import de.jayasinghe.samples.notification.orders.OrderPlacedReceivedEvent;
import de.jayasinghe.samples.notification.orders.OrderShippedReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IncomingOrderController {

	private static final Logger logger = LoggerFactory.getLogger(IncomingOrderController.class);
	private final ApplicationEventPublisher applicationEventPublisher;

	public IncomingOrderController(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@PostMapping("/pizzaOrders")
	public void orderPlaced(@RequestBody PizzaOrder pizzaOrderPlaced) {

		applicationEventPublisher.publishEvent(new OrderPlacedReceivedEvent(pizzaOrderPlaced.toOrder()));
		logger.info("Received PizzaOrderPlaced {} event and successfully dispatched for further processing",
				pizzaOrderPlaced.getOrderId());
	}

	@PutMapping("/pizzaOrders/shipped")
	public void orderShipped(@RequestBody PizzaOrder pizzaOrderShipped) {

		applicationEventPublisher.publishEvent(new OrderShippedReceivedEvent(pizzaOrderShipped.toOrder()));
		logger.info("Received PizzaOrderShipped {} event and successfully dispatched for further processing",
				pizzaOrderShipped.getOrderId());
	}

}
