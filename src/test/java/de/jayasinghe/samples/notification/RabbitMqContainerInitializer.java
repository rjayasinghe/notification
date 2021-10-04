package de.jayasinghe.samples.notification;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.GenericContainer;

public class RabbitMqContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	private static final int PORT = 5672;

	static GenericContainer<?> rabbitContainer = new GenericContainer<>("rabbitmq:3.9-management")
        .withExposedPorts(PORT)
        .withReuse(true);


	@Override
	public void initialize(ConfigurableApplicationContext context) {
        rabbitContainer.start();

        String redisContainerIP = "spring.rabbitmq.host=" + rabbitContainer.getContainerIpAddress();
        String redisContainerPort = "spring.rabbitmq.port=" + rabbitContainer.getMappedPort(PORT);
        TestPropertySourceUtils.addInlinedPropertiesToEnvironment(context,  redisContainerIP, redisContainerPort);
		
	}
}
