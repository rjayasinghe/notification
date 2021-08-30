package de.jayasinghe.samples.notification.dispatch.statemachine;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.service.DefaultStateMachineService;
import org.springframework.statemachine.service.StateMachineService;

@Configuration
public class OrderStateMachineRuntimeConfig {

	@Bean
	public StateMachineService<OrderStates, OrderEvents> stateMachineService(
			StateMachineFactory<OrderStates, OrderEvents> stateMachineFactory) {

		return new DefaultStateMachineService<>(stateMachineFactory);
	}

}
