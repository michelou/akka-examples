package akka.examples;

public class Order {
    private Integer orderId;
    private String city;

    public Order(Integer orderId, String city) {
        this.orderId = orderId;
        this.city = city;
    }

    public static Order BAD_ORDER = new Order(-1, "");
}
