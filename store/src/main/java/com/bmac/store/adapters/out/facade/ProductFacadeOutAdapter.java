package com.bmac.store.adapters.out.facade;

import com.bmac.common.domain.CommonEntities;
import com.bmac.common.facade.FactoryCommonEntityFacade;
import com.bmac.store.ports.out.product.LoadCommonProductPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductFacadeOutAdapter implements LoadCommonProductPort {

    private final FactoryCommonEntityFacade<CommonEntities.Product> facade;

    public ProductFacadeOutAdapter(@Autowired(required = false) FactoryCommonEntityFacade<CommonEntities.Product> facade) {
        this.facade = facade;
    }

    @Override
    public List<CommonEntities.Product> loadAll() {
        return facade.loadAll();
    }
}
