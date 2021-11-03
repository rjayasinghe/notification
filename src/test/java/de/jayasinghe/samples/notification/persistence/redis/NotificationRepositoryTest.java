package de.jayasinghe.samples.notification.persistence.redis;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import de.jayasinghe.samples.notification.RedisContainerInitializer;

@SpringBootTest
@ContextConfiguration(initializers = RedisContainerInitializer.class)
class NotificationRepositoryTest {

	@Autowired
	private NotificationRepository repository;

	@Test
	void testThatSavedEntityCanBeReadAgain() {
		Notification notification = new Notification("orderId", "foo", "1234");
		repository.save(notification);

		Optional<Notification> maybeFoundNotification = repository.findById("orderId");
		assertTrue(maybeFoundNotification.isPresent());
		assertEquals("foo", maybeFoundNotification.get().getMessage());
	}
}
