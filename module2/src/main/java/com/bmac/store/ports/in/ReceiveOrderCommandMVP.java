package com.bmac.store.ports.in;

import java.util.UUID;

// Todo: after MVP: this command will need to be extended with more parameters
public record ReceiveOrderCommandMVP(UUID productID, int amount) { }
