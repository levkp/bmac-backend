package com.bmac.warehouse.adapters.out.jpa.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<ItemJpaEntity, UUID> { }
