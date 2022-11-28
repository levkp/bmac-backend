package com.bmac.store;

import com.bmac.common.cutoff.DailyCutoffTime;
import com.bmac.store.domain.Batch;
import com.bmac.store.ports.out.batch.BatchCreatePort;
import com.bmac.store.ports.out.batch.BatchLoadPort;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CustomerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    BatchLoadPort port;

    @Test
    public void test1() {
        // Arrange
        LocalDate future = LocalDate.of(2122, 11, 26);
        // Act
        Optional<Batch> optional = port.loadByDateTime(future);
        // Assert
        assertTrue(optional.isEmpty());
    }

    @Autowired
    BatchCreatePort createPort;

    @Test
    public void test2() {
        // Arrange
        LocalDate date = LocalDate.now();
        if (DailyCutoffTime.hasPassed()) {
            date = LocalDate.now().plusDays(1);
        }
        // Act
        Batch batch = createPort.create(date);
        // Assert
        assertEquals(batch.getDate(), date);
//        assertEquals(batch.getStatus(), BatchStatus.ACTIVE);
    }
}
