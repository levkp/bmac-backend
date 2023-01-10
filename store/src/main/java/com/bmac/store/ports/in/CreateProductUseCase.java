package com.bmac.store.ports.in;

import com.bmac.store.domain.Product;

public interface CreateProductUseCase {
    Product create(CreateProductCommand command);
}
