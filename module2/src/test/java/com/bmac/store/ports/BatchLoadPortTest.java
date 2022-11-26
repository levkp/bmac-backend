package com.bmac.store.ports;

import com.bmac.store.adapters.out.db.BatchRepositoryAdapter;
import com.bmac.store.domain.Batch;
import com.bmac.store.ports.out.BatchLoadPort;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BatchLoadPortTest {

    @Autowired
    BatchLoadPort port;

    @Test
    public void test1() {
        // Arrange
        LocalDateTime future = LocalDate.of(2122, 11, 26).atTime(LocalTime.now());
        // Act
        Optional<Batch> optional = port.loadByDateTime(future);
        // Assert
        assertTrue(optional.isEmpty());
    }
}
