package com.bmac.store.bootstrap;

import com.bmac.common.domain.Product;
import com.bmac.store.ports.in.order.ReceiveOrderCommand;
import com.bmac.store.ports.in.order.ReceiveOrderUseCase;
import com.bmac.store.ports.in.product.CreateProductCommand;
import com.bmac.store.ports.in.product.CreateProductUseCase;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Profile("seed")
public class DataSeeder implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final CreateProductUseCase createProduct;
    private final ReceiveOrderUseCase receiveOrder;

    @Autowired
    public DataSeeder(CreateProductUseCase createProduct, ReceiveOrderUseCase receiveOrder) {
        this.createProduct = createProduct;
        this.receiveOrder = receiveOrder;
    }

    @Override
    public void run(String... args) {
        log.info("Seeding database with fake products");

        Faker faker = new Faker();
        Random random = new Random();
        List<UUID> productIds = new ArrayList<>();

        for(int i = 0; i < 100; i++) {
            String food = faker.food().dish(); // Sadly, JavaFaker can't do desserts
            // Todo: round to two decimal places
            double price = random.nextDouble(1.0, 9.9);
            Product product = createProduct.create(new CreateProductCommand(food, price));
            productIds.add(product.getId());
        }

        log.info("Seeding database with fake orders");

        // Creating 20 fake orders
        for(int i = 0; i < 20; i++) {
            Map<UUID, Integer> orderLine = new HashMap<>();
            // Let's say one order can contain between 1 and 10 items
            for(int j = 0; j < random.nextInt(1, 10); j++) {
                // Let's say one item can be ordered between 1 and 15 times at once
                orderLine.put(productIds.get(random.nextInt(productIds.size())), random.nextInt(1, 15));
            }
            receiveOrder.receive(new ReceiveOrderCommand(orderLine));
        }
    }
}
