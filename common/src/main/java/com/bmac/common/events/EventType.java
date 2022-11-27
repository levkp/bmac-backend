package com.bmac.common.events;

public enum EventType {

    StoreOrderBatchForwarded,
    FactoryOrderBatchReceived,

    FactoryDeliveryCreated,

    FactoryIngredientRequestCreated,
    FactoryIngredientDeliveryReceived,

    WarehouseOutboundOrderReceived,
    WarehouseDeliveryCreated,

    WarehouseInboundOrderCreated,
    WarehouseShipmentReceived
}
