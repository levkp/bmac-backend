package com.bmac.store.core;

import com.bmac.store.domain.StoreProduct;
import com.bmac.store.ports.in.product.CreateStoreProductCommand;
import com.bmac.store.ports.in.product.CreateStoreProductUseCase;
import com.bmac.store.ports.out.product.CreateStoreProductPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultCreateStoreProductUseCase implements CreateStoreProductUseCase {

    private final CreateStoreProductPort productCreator;

    @Autowired
    public DefaultCreateStoreProductUseCase(CreateStoreProductPort productCreator) {
        this.productCreator = productCreator;
    }

    @Override
    public StoreProduct create(CreateStoreProductCommand command) {
        StoreProduct product = new StoreProduct(command.id(), command.name(), command.price());
        productCreator.create(product);
        return product;
    }
}
