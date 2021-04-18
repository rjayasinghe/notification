package de.jayasinghe.samples.notification;

import java.util.UUID;

public class Notification {
    private final UUID orderId;
    private final String message;
    private final String mobileNumber;

    public Notification(UUID orderId, String message, String mobileNumber) {
        this.orderId = orderId;
        this.message = message;
        this.mobileNumber = mobileNumber;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public String getMessage() {
        return message;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
}