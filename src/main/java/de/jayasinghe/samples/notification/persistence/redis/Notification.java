package de.jayasinghe.samples.notification.persistence.redis;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Notification")
public class Notification implements Serializable {
    @Id
    private final String orderId;
    private final String message;
    private final String mobileNumber;

    public Notification(String orderId, String message, String mobileNumber) {
        this.orderId = orderId;
        this.message = message;
        this.mobileNumber = mobileNumber;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getMessage() {
        return message;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
}