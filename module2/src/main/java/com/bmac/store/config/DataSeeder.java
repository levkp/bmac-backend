package com.bmac.store.config;

import com.bmac.store.domain.Product;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

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
        Faker faker = new Faker();
        Random random = new Random();
        List<UUID> productIds = new ArrayList<>();

        log.info("Creating fake products");

        for(int i = 0; i < 100; i++) {
            String food = faker.food().dish(); // Sadly, JavaFaker can't do desserts
            double price = random.nextDouble(1.0, 9.9);
            Product product = createProduct.create(new CreateProductCommand(food, price));
            productIds.add(product.getId());
        }

        for(UUID id : productIds) {

        }
    }

//
//        log.info("Creating fake orders");
//        for(UUID uuid : productUuids) {
//            orderReceiver.receive(new ReceiveOrderCommand(uuid, random.nextInt(1, 20)));
//        }
//    }
}
