package com.bmac.store.core;

import com.bmac.store.domain.Product;
import com.bmac.store.ports.in.product.CreateProductCommand;
import com.bmac.store.ports.in.product.CreateProductUseCase;
import com.bmac.store.ports.out.product.ProductCreatePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultCreateProductUseCase implements CreateProductUseCase {

    private final ProductCreatePort productCreator;

    @Autowired
    public DefaultCreateProductUseCase(ProductCreatePort productCreator) {
        this.productCreator = productCreator;
    }

    @Override
    public void create(CreateProductCommand command) {
        productCreator.create(new Product(command.name(), command.price()));
    }
}
