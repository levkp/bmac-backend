package com.bmac.store.core.exception;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(UUID uuid) {
        super("Unable to find product with ID " + uuid.toString());
    }
}
