package com.bmac.store.adapters.out.db.entities;

import com.bmac.store.adapters.out.db.ProductRepository;
import com.bmac.store.domain.Product;
import com.bmac.store.ports.out.ProductCreatePort;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryAdapter implements ProductCreatePort {

    private final ProductRepository repository;

    public ProductRepositoryAdapter(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Product p) {
        ProductEntity jpaEntity = new ProductEntity(p.getUuid(), p.getName(), p.getPrice());
        repository.save(jpaEntity);
    }
}
