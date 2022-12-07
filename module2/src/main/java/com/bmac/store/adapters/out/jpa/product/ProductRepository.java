package com.bmac.store.adapters.out.jpa.product;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductJpaEntity, UUID> {

}