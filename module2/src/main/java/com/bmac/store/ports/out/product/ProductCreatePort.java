package com.bmac.store.ports.out.product;

import com.bmac.common.domain.Product;

public interface ProductCreatePort {
    void create(Product p);
}
