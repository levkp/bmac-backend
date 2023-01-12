package com.bmac.store.adapters.out.jpa.product;

import com.bmac.store.domain.StoreProduct;
import com.bmac.store.ports.out.product.CreateStoreProductPort;
import com.bmac.store.ports.out.product.LoadStoreProductPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class StoreProductRepositoryAdapter implements CreateStoreProductPort, LoadStoreProductPort {

    private final StoreProductRepository repository;

    public StoreProductRepositoryAdapter(StoreProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(StoreProduct product) {
        StoreProductJpaEntity jpaEntity = new StoreProductJpaEntity(product.getId(), product.getName(), product.getPrice());
        repository.save(jpaEntity);
    }

    @Override
    public Optional<StoreProduct> loadById(UUID uuid) {
        Optional<StoreProductJpaEntity> optional = repository.findById(uuid);
        if (optional.isPresent()) {
            StoreProductJpaEntity jpaEntity = optional.get();
            return Optional.of(new StoreProduct(jpaEntity.getId(), jpaEntity.getName(), jpaEntity.getPrice()));
        }
        return Optional.empty();
    }

    @Override
    public List<StoreProduct> loadAllByIds(List<UUID> ids) {
        return repository.findAllById(ids).stream().map(this::fromJpaEntity).toList();
    }

    @Override
    public List<StoreProduct> loadAll() {
        return repository.findAll().stream().map(this::fromJpaEntity).toList();
    }

    private StoreProduct fromJpaEntity(StoreProductJpaEntity jpaEntity) {
        return new StoreProduct(jpaEntity.getId(), jpaEntity.getName(), jpaEntity.getPrice());
    }

    private StoreProductJpaEntity toJpaEntity(StoreProduct entity) {
        return new StoreProductJpaEntity(entity.getId(), entity.getName(), entity.getPrice());
    }
}
