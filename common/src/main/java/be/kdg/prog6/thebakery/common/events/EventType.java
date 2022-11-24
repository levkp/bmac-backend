package be.kdg.prog6.thebakery.common.events;

public enum EventType {
    StoreOrderReceived,
    StoreOrderForwarded,

    FactoryOrderReceived,
    FactoryDeliveryCreated,

    FactoryIngredientRequestCreated,
    FactoryIngredientDeliveryReceived,

    WarehouseOutboundOrderReceived,
    WarehouseDeliveryCreated,

    WarehouseInboundOrderCreated,
    WarehouseShipmentReceived
}
