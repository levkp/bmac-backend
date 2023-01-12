package com.bmac.factory.adapters.out.jpa.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductJpaEntity, UUID> { }
