package com.bmac.store.core;

import com.bmac.store.domain.Product;
import com.bmac.store.ports.in.product.CreateProductCommand;
import com.bmac.store.ports.in.product.CreateProductUseCase;
import com.bmac.store.ports.out.product.ProductCreatePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultCreateProductUseCase implements CreateProductUseCase {

    private final ProductCreatePort productCreator;

    @Autowired
    public DefaultCreateProductUseCase(ProductCreatePort productCreator) {
        this.productCreator = productCreator;
    }

    @Override
    public Product create(CreateProductCommand command) {
        Product product = new Product(UUID.randomUUID(), command.name(), command.price());
        productCreator.create(product);
        return product;
    }
}
