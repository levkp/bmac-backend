package com.bmac.store.ports.out.product;

import com.bmac.store.domain.StoreProduct;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadStoreProductPort {
    Optional<StoreProduct> loadById(UUID id);

    List<StoreProduct> loadAllByIds(List<UUID> ids);

    List<StoreProduct> loadAll();
}
