package com.bmac.factory.adapters.in.facade;

import com.bmac.common.domain.CommonEntities;
import com.bmac.common.facade.FactoryCommonEntityFacade;
import com.bmac.factory.domain.Product;
import com.bmac.factory.ports.out.LoadFactoryEntityPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductFacadeInAdapter implements FactoryCommonEntityFacade<CommonEntities.Product> {

    private final LoadFactoryEntityPort<Product> productLoader;

    @Autowired
    public ProductFacadeInAdapter(LoadFactoryEntityPort<Product> productLoader) {
        this.productLoader = productLoader;
    }

    @Override
    public List<CommonEntities.Product> loadAll() {
        return productLoader.loadAll()
                .stream()
                .map(product -> new CommonEntities.Product(product.getId(), product.getName()))
                .collect(Collectors.toList());
    }
}
