package com.bmac.store.ports.out;

import com.bmac.store.domain.Order;

public interface OrderReceivePort {
    void receive(Order o);
}
