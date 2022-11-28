package com.bmac.store.config;

import com.bmac.store.ports.in.product.CreateProductCommand;
import com.bmac.store.ports.in.product.CreateProductUseCase;
import com.bmac.store.ports.in.order.ReceiveOrderUseCase;
import com.bmac.store.ports.out.batch.BatchCreatePort;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Random;

@Component
@Profile("dev")
public class DataSeeder implements CommandLineRunner {
    Logger log = LoggerFactory.getLogger(getClass());
    CreateProductUseCase productCreator;
    ReceiveOrderUseCase orderReceiver;
    BatchCreatePort batchCreator;

    @Autowired
    public DataSeeder(CreateProductUseCase productUseCase, ReceiveOrderUseCase orderReceiver, BatchCreatePort batchCreator) {
        this.productCreator = productUseCase;
        this.orderReceiver = orderReceiver;
        this.batchCreator = batchCreator;
    }

    @Override
    public void run(String... args) {
        log.info("Seeding database");

        Faker faker = new Faker();
        Random random = new Random();
        DecimalFormat priceFormat = new DecimalFormat("0.00");

        for(int i = 0; i < 100; i++) {
            String food = faker.food().dish(); // Sadly, JavaFaker can't do desserts
            // Todo: round to 2 decimal places
            double price = random.nextDouble(1.0, 9.9);
            productCreator.create(new CreateProductCommand(food, price));
        }

        log.info("Creating new batch");
        batchCreator.create(LocalDate.now());


    }
}
