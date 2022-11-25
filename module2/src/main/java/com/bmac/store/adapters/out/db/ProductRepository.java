package com.bmac.store.adapters.out.db;

import com.bmac.store.adapters.out.db.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {


}