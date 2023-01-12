package com.bmac.factory.core;

import com.bmac.factory.domain.Product;
import com.bmac.factory.ports.in.CreateProductCommand;
import com.bmac.factory.ports.in.CreateProductUseCase;
import com.bmac.factory.ports.out.CreateFactoryEntityPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultCreateProductUseCase implements CreateProductUseCase {

    private final CreateFactoryEntityPort<Product> productCreator;

    @Autowired
    public DefaultCreateProductUseCase(CreateFactoryEntityPort<Product> productCreator) {
        this.productCreator = productCreator;
    }

    @Override
    public void create(CreateProductCommand command) {
        productCreator.create(new Product(UUID.randomUUID(), command.name()));
    }
}
