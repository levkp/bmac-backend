package com.bmac.store.ports.out.product;

import com.bmac.store.domain.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductLoadPort {
    Optional<Product> load(UUID uuid);
}