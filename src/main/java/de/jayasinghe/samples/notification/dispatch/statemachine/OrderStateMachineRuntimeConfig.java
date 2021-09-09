package de.jayasinghe.samples.notification.dispatch.statemachine;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.data.redis.RedisStateMachineContextRepository;
import org.springframework.statemachine.persist.RepositoryStateMachinePersist;
import org.springframework.statemachine.service.DefaultStateMachineService;
import org.springframework.statemachine.service.StateMachineService;

@Configuration
public class OrderStateMachineRuntimeConfig {

	@Bean
	public StateMachineService<OrderStates, OrderEvents> stateMachineService(
			StateMachineFactory<OrderStates, OrderEvents> stateMachineFactory,
			StateMachinePersist<OrderStates, OrderEvents, String> stateMachinePersist) {

		return new DefaultStateMachineService<>(stateMachineFactory, stateMachinePersist);
	}

	@Bean
	public StateMachinePersist<OrderStates, OrderEvents, String> stateMachinePersist(
			RedisConnectionFactory connectionFactory) {
		RedisStateMachineContextRepository<OrderStates, OrderEvents> repository = new RedisStateMachineContextRepository<>(
				connectionFactory);
		return new RepositoryStateMachinePersist<>(repository);
	}
}
