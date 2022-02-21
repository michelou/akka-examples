package akka.examples;

import java.time.Instant;

public class OrderHistory {
    private Order order;
    private Address address;
    private Instant timestamp;

    public OrderHistory(Order order, Address address) {
        this.order = order;
        this.address = address;
        this.timestamp = Instant.now();
    }

}
