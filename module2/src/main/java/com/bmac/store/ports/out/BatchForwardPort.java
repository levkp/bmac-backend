package com.bmac.store.ports.out;

import com.bmac.store.domain.Batch;
import com.bmac.store.domain.Order;

import java.util.List;

public interface BatchForwardPort {
    void forward(Batch batch, List<Order> orderLine);
}
