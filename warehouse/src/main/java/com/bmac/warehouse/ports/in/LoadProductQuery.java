package com.bmac.warehouse.ports.in;

import com.bmac.common.domain.CommonEntities;

import java.util.List;

public interface LoadProductQuery {
    List<CommonEntities.Product> loadAll();
}
