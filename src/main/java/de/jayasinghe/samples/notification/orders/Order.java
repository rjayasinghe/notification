package de.jayasinghe.samples.notification.orders;

import java.util.List;
import java.util.UUID;

public class Order {
    private final UUID orderId;
    private final Customer customer;
    private final List<OrderItems> orderItems;

    public Order(UUID orderId, Customer customer, List<OrderItems> orderItems) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderItems = orderItems;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItems> getOrderItems() {
        return orderItems;
    }
}
