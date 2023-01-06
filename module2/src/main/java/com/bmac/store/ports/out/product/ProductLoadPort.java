package com.bmac.store.ports.out.product;

import com.bmac.store.domain.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductLoadPort {
    Optional<Product> loadById(UUID id);

    List<Product> loadAllByIds(List<UUID> ids);
}
