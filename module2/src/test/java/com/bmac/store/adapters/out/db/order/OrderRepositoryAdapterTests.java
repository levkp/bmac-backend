package com.bmac.store.adapters.out.db.order;

import com.bmac.store.ports.in.product.CreateProductCommand;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class OrderRepositoryAdapterTests {

    @Autowired
    OrderRepositoryAdapter repository;

    @BeforeAll
    void seed() {

    }

    @Test
    public void loadAllByBatchUuidTest() {

    }
}
