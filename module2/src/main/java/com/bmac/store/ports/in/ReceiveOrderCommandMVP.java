package com.bmac.store.ports.in;

import java.util.UUID;

// Todo: MVP only: this command will need to be extended with more parameters later
public record ReceiveOrderCommandMVP(UUID productID, int amount) { }
