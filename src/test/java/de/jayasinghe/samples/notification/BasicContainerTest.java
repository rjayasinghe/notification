package de.jayasinghe.samples.notification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;

class BasicContainerTest {

	private static final int PORT = 6379;

	static GenericContainer<?> redisContainer = new GenericContainer<>("redis:6-alpine").withExposedPorts(PORT)
			.withReuse(true);

	@Test
	void ensureThatContainerIsUp() {
		redisContainer.start();
		assertTrue(redisContainer.isRunning());
	}
}
