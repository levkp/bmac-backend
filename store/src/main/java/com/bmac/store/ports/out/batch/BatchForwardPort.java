package com.bmac.store.ports.out.batch;

import com.bmac.store.domain.Batch;
import com.bmac.store.domain.StoreOrder;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface BatchForwardPort {
    void forward(Batch batch, List<StoreOrder> orderLine) throws JsonProcessingException;
}
