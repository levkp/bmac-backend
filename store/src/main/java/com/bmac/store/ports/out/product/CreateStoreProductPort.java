package com.bmac.store.ports.out.product;

import com.bmac.store.domain.StoreProduct;

public interface CreateStoreProductPort {
    void create(StoreProduct p);
}
