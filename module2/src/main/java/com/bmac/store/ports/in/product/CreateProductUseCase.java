package com.bmac.store.ports.in.product;

import com.bmac.common.domain.Product;

public interface CreateProductUseCase {
    Product create(CreateProductCommand command);
}
