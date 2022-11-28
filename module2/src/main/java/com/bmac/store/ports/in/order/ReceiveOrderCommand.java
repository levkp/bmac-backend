package com.bmac.store.ports.in.order;

import java.util.UUID;

// Todo: after MVP: this command will need to be extended with more parameters
public record ReceiveOrderCommand(UUID productID, int amount) { }
