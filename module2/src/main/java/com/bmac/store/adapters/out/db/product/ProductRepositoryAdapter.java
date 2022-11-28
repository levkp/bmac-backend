package com.bmac.store.adapters.out.db.product;

import com.bmac.store.domain.Product;
import com.bmac.store.ports.out.product.ProductCreatePort;
import com.bmac.store.ports.out.product.ProductLoadPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductRepositoryAdapter implements ProductCreatePort, ProductLoadPort {

    private final ProductRepository repository;

    public ProductRepositoryAdapter(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Product p) {
        ProductEntity jpaEntity = new ProductEntity(p.getUuid(), p.getName(), p.getPrice());
        repository.save(jpaEntity);
    }

    @Override
    public Optional<Product> load(UUID uuid) {
        Optional<ProductEntity> optional = repository.findById(uuid);
        if (optional.isPresent()) {
            ProductEntity jpaEntity = optional.get();
            return Optional.of(new Product(jpaEntity.getUuid(), jpaEntity.getName(), jpaEntity.getPrice()));
        }
        return Optional.empty();
    }
}
