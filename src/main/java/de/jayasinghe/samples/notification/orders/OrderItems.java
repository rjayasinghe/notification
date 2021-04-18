package de.jayasinghe.samples.notification.orders;

import java.math.BigDecimal;

public class OrderItems {
    private final BigDecimal price;
    private final String name;

    public OrderItems(BigDecimal price, String name) {
        this.price = price;
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
