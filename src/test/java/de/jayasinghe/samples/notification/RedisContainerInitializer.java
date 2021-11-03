package de.jayasinghe.samples.notification;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.GenericContainer;

public class RedisContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final Integer PORT = 6379;

	static GenericContainer<?> redisContainer = new GenericContainer<>("redis:6-alpine")
        .withExposedPorts(PORT)
        .withReuse(true);

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        // Start container
        redisContainer.start();

        String redisContainerIP = "spring.redis.host=" + redisContainer.getContainerIpAddress();
        String redisContainerPort = "spring.redis.port=" + redisContainer.getMappedPort(PORT);
        TestPropertySourceUtils.addInlinedPropertiesToEnvironment(context,  redisContainerIP, redisContainerPort);
    }
}