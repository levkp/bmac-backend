package com.bmac.store.ports.out;

import com.bmac.common.domain.Product;

public interface ProductCreatePort {
    void create(Product p);
}
