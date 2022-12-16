package com.bmac.store.ports.out;

import com.bmac.store.domain.Batch;
import com.bmac.store.domain.Order;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface BatchForwardPort {
    void forward(Batch batch, List<Order> orderLine) throws JsonProcessingException;
}
