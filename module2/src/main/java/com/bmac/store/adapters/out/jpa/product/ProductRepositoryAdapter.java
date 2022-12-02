//package com.bmac.store.adapters.out.db.product;
//
//import com.bmac.store.domain.Product;
//import com.bmac.store.ports.out.product.ProductCreatePort;
//import com.bmac.store.ports.out.product.ProductLoadPort;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//import java.util.UUID;
//
//@Repository
//public class ProductRepositoryAdapter implements ProductCreatePort, ProductLoadPort {
//
//    private final ProductRepository repository;
//
//    public ProductRepositoryAdapter(ProductRepository repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public void create(Product product) {
//        ProductJpaEntity jpaEntity = new ProductJpaEntity(product.getUuid(), product.getName(), product.getPrice());
//        repository.save(jpaEntity);
//    }
//
//    @Override
//    public Optional<Product> load(UUID uuid) {
//        Optional<ProductJpaEntity> optional = repository.findById(uuid);
//        if (optional.isPresent()) {
//            ProductJpaEntity jpaEntity = optional.get();
//            return Optional.of(new Product(jpaEntity.getId(), jpaEntity.getName(), jpaEntity.getPrice()));
//        }
//        return Optional.empty();
//    }
//}
