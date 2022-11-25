package com.bmac.store.core;

import com.bmac.store.ports.in.CreateProductCommandMVP;
import com.bmac.store.ports.in.CreateProductUseCase;
import com.bmac.store.ports.out.ProductCreatePort;
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
    public void create(CreateProductCommandMVP command) {

    }
}
