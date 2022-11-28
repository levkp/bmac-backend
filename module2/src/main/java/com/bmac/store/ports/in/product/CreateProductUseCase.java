package com.bmac.store.ports.in.product;

// Todo: after MVP: this command will need to be extended with more parameters (ingredients to forward to warehouse)
public interface CreateProductUseCase {
    void create(CreateProductCommand command);
}
