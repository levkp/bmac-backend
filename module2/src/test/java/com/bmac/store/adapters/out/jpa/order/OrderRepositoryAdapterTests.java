//package com.bmac.store.adapters.out.db.order;
//
//import com.bmac.store.ports.out.batch.DailyBatchUuidQuery;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//import java.util.UUID;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@SpringBootTest
//public class OrderRepositoryAdapterTests {
//
//    @Autowired
//    OrderRepositoryAdapter repository;
//
//    @Autowired
//    DailyBatchUuidQuery batchUuidQuery;
//
//    @Test
//    public void loadAllByBatchUuidTest() {
//        // Arrange
//        UUID batchUuid = batchUuidQuery.getDailyBatchUuid();
//
//        // Act
//        List<OrderJpaEntity> orders = repository.loadAllByBatchUuid(batchUuid);
//
//        // Assert
//        assertTrue(orders.size() > 0);
//    }
//}
