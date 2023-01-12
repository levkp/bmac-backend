package com.bmac.store.ports.in.product;

import com.bmac.store.domain.StoreProduct;

public interface CreateStoreProductUseCase {
    StoreProduct create(CreateStoreProductCommand command);
}
