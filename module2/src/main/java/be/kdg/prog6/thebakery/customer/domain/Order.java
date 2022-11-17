package be.kdg.prog6.thebakery.customer.domain;

import java.time.LocalDateTime;
import java.util.Map;

@SuppressWarnings("unused")
public class Order {
    LocalDateTime timestamp;
    Map<Product, Integer> orderLine;

}
