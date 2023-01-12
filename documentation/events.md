# Programming 6 - Event catalog of B-MaC


## Event catalog

| Event                          | Source context | Destination context |
|--------------------------------|----------------|---------------------|
| `StoreBatchForwardedEvent`     | Store          | All                 |
| `WarehouseInboundOrderCreated` | Warehouse      | Factory             |
| `WarehouseDeliveryCreated`     | Warehouse      | Factory             |
| `FactoryDeliveryReceived`      | Factory        | Warehouse           |


## Flow

Store receives all orders and cancellations and puts them in the daily batch. After the cutoff time, it creates a 
`StoreBatchForwarded` event that contains each uncancelled inboundOrder and their content: what product was ordered in what 
quantity. Both Factory and Warehouse receives this message. Factory now knows that it should expect a new message from 
Warehouse soon. Based on the information provided by Store, Warehouse can calculate whether it all necessary 
ingredients are in stock to create a delivery. If yes, it sends a `WarehouseDeliveryCreated` event to Factory who now 
knows the ingredients they need to start cooking are already on their way. This is the ideal and most likely scenario, 
as Warehouse is responsible to maintain their stock level during the day by creating inbound orders. However, if for 
some reason there isn't enough stock to create a delivery, Factory receives a `WarehouseInboundOrderCreated` event to know
ingredients arrive later than usual. After Warehouse receives the inbound inboundOrder, it creates a `WarehouseDeliveryCreated`
event. Whenever Factory receives the ingredients, it sends a `FactoryDeliveryReceived` event to Warehouse to let it know 
the delivery was successful.
