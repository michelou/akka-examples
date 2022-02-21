package akka.examples;

public class Address {
    private String city;
    private String street;
    private Integer number;

    public Address(String city, String street, Integer number) {
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public static Address BAD_ADDRESS = new Address("", "", -1);
}
