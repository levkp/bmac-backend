package com.bmac.store.adapters.out.db;

import com.bmac.store.adapters.out.db.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {


}
