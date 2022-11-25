package com.bmac.store.ports.out;

import com.bmac.store.domain.Product;

public interface ProductCreatePort {
    void create(Product p);
}
