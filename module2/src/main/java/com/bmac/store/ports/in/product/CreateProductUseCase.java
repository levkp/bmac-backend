package com.bmac.store.ports.in.product;

import com.bmac.store.domain.Product;

// Todo: after MVP: this command will need to be extended with more parameters (ingredients to forward to warehouse)
public interface CreateProductUseCase {
    Product create(CreateProductCommand command);
}
