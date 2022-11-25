package com.bmac.store.ports.in;

// Todo: after MVP: this command will need to be extended with more parameters (ingredients to forward to warehouse)
public interface CreateProductUseCase {
    void create(CreateProductCommandMVP command);
}
