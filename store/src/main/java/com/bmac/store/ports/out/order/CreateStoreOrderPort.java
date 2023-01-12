package com.bmac.store.ports.out.order;

import com.bmac.store.domain.StoreOrder;

public interface CreateStoreOrderPort {
    void create(StoreOrder order);
}
