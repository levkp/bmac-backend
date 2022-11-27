package com.bmac.store.ports.out;

import com.bmac.store.adapters.out.db.OrderActivityEntity;

import java.util.UUID;

public interface OrderActivityCreatePort {
    void createActivity(UUID orderUuid, OrderActivityEntity.OrderAction action);
}
