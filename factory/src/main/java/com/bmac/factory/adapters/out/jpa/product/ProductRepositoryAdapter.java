package com.bmac.factory.adapters.out.jpa.product;

import com.bmac.common.exception.EntityConstraintException;
import com.bmac.factory.domain.Product;
import com.bmac.factory.ports.out.CreateFactoryEntityPort;
import com.bmac.factory.ports.out.LoadFactoryEntityPort;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryAdapter implements CreateFactoryEntityPort<Product>, LoadFactoryEntityPort<Product> {

    private final ProductRepository repository;

    public ProductRepositoryAdapter(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Product product) {
        try {
            repository.save(toJpaEntity(product));
        } catch (DataIntegrityViolationException exception) {
            throw new EntityConstraintException(exception.getMessage());
        }
    }

    @Override
    public List<Product> loadAll() {
        return repository.findAll().stream().map(this::fromJpaEntity).toList();
    }

    private Product fromJpaEntity(ProductJpaEntity jpaEntity) {
        return new Product(jpaEntity.getId(), jpaEntity.getName());
    }
    private ProductJpaEntity toJpaEntity(Product product) {
        return new ProductJpaEntity(product.getId(), product.getName());
    }
}
