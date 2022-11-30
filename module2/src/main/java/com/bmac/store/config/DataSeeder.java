package com.bmac.store.config;

import com.bmac.store.domain.Product;
import com.bmac.store.ports.in.order.ReceiveOrderCommand;
import com.bmac.store.ports.in.product.CreateProductCommand;
import com.bmac.store.ports.in.product.CreateProductUseCase;
import com.bmac.store.ports.in.order.ReceiveOrderUseCase;
import com.bmac.store.ports.out.batch.BatchCreatePort;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
@Profile("dev")
public class DataSeeder implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final CreateProductUseCase productCreator;
    private final ReceiveOrderUseCase orderReceiver;
    private final BatchCreatePort batchCreator;

    @Autowired
    public DataSeeder(CreateProductUseCase productUseCase, ReceiveOrderUseCase orderReceiver, BatchCreatePort batchCreator) {
        this.productCreator = productUseCase;
        this.orderReceiver = orderReceiver;
        this.batchCreator = batchCreator;
    }

    @Override
    public void run(String... args) {
        Faker faker = new Faker();
        Random random = new Random();
        List<UUID> productUuids = new ArrayList<>();

        log.info("Creating fake products");
        for(int i = 0; i < 100; i++) {
            String food = faker.food().dish(); // Sadly, JavaFaker can't do desserts
            // Todo: round to 2 decimal places
            double price = random.nextDouble(1.0, 9.9);
            Product product = productCreator.create(new CreateProductCommand(food, price));
            productUuids.add(product.getUuid());
        }

        log.info("Creating fake batch");
        batchCreator.create(LocalDate.now());

        log.info("Creating fake orders");
        for(UUID uuid : productUuids) {
            orderReceiver.receive(new ReceiveOrderCommand(uuid, random.nextInt(1, 20)));
        }
    }
}
