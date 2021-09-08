package de.jayasinghe.samples.notification.persistence;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest
@ContextConfiguration(initializers = NotificationRepositoryTest.Initializer.class)
public class NotificationRepositoryTest {

	@Autowired
	private NotificationRepository repository;

	@Test
	void testThatItWorks() {
		Notification notification = new Notification("orderId", "foo", "1234");
		repository.save(notification);

		Optional<Notification> maybeFoundNotification = repository.findById("orderId");
		assertTrue(maybeFoundNotification.isPresent());
		assertEquals("foo", maybeFoundNotification.get().getMessage());
	}

	public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

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
}
