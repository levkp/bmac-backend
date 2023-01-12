package com.bmac.common.facade;

import com.bmac.common.domain.CommonEntities;

import java.util.List;

public interface FactoryCommonEntityFacade<T extends CommonEntities.CommonEntity> {
    List<T> loadAll();
}
