package com.bmac.store.ports.out.product;

import com.bmac.common.domain.CommonEntities;

import java.util.List;

public interface LoadCommonProductPort {
    List<CommonEntities.Product> loadAll();
}
