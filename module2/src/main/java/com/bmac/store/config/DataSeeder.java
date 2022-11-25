package com.bmac.store.config;

import com.bmac.store.ports.in.CreateProductCommandMVP;
import com.bmac.store.ports.in.CreateProductUseCase;
import com.bmac.store.ports.in.ReceiveOrderUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DataSeeder implements CommandLineRunner {
    Logger log = LoggerFactory.getLogger(getClass());
    CreateProductUseCase productCreator;
    ReceiveOrderUseCase orderReceiver;

    @Autowired
    public DataSeeder(CreateProductUseCase productUseCase, ReceiveOrderUseCase orderReceiver) {
        this.productCreator = productUseCase;
        this.orderReceiver = orderReceiver;
    }

    @Override
    public void run(String... args) {
        log.info("Seeding database");
        productCreator.create(new CreateProductCommandMVP("Raspberry cake", 5.9));
    }
}
