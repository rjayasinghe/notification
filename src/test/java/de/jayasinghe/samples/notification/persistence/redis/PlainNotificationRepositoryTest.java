package de.jayasinghe.samples.notification.persistence.redis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlainNotificationRepositoryTest {

	/*
	static GenericContainer<?> redisContainer = new GenericContainer<>("redis:6-alpine")
        .withExposedPorts(PORT)
        .withReuse(true);
	*/


	@Autowired
	private NotificationRepository repository;

	@Test
	@Disabled("does not work without manually started container")
	void testThatSavedEntityCanBeReadAgain() {
		Notification notification = new Notification("orderId", "foo", "1234");
		repository.save(notification);

		Optional<Notification> maybeFoundNotification = repository.findById("orderId");
		assertTrue(maybeFoundNotification.isPresent());
		assertEquals("foo", maybeFoundNotification.get().getMessage());
	}	
}
