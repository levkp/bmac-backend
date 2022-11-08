package com.bmac.store.ports.in;

import com.bmac.common.domain.Product;

public interface CreateProductUseCase {
    Product create(CreateProductCommand command);
}
