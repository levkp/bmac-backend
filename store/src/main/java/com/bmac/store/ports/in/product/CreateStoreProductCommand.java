package com.bmac.store.ports.in.product;

import java.util.UUID;

public record CreateStoreProductCommand(UUID id, String name, double price) {

}
