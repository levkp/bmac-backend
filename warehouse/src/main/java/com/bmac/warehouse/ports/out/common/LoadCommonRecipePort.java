package com.bmac.warehouse.ports.out.common;

import com.bmac.common.domain.CommonEntities;

import java.util.List;
import java.util.UUID;

public interface LoadCommonRecipePort {
    List<CommonEntities.Recipe> loadByProductIds(List<UUID> productIds);
}
