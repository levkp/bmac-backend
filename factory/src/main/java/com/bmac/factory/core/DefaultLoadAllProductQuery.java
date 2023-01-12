package com.bmac.factory.core;

import com.bmac.factory.domain.Product;
import com.bmac.factory.ports.in.LoadAllFactoryEntityQuery;
import com.bmac.factory.ports.out.LoadFactoryEntityPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultLoadAllProductQuery implements LoadAllFactoryEntityQuery<Product> {

    private final LoadFactoryEntityPort<Product> productLoader;

    public DefaultLoadAllProductQuery(LoadFactoryEntityPort<Product> productLoader) {
        this.productLoader = productLoader;
    }

    @Override
    public List<Product> loadAll() {
        return productLoader.loadAll();
    }
}
