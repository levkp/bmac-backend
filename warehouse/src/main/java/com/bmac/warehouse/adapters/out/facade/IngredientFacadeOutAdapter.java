package com.bmac.warehouse.adapters.out.facade;

import com.bmac.common.domain.CommonEntities;
import com.bmac.common.facade.CommonEntityFacade;
import com.bmac.warehouse.ports.out.common.LoadCommonIngredientPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IngredientFacadeOutAdapter implements LoadCommonIngredientPort {

    private final CommonEntityFacade<CommonEntities.Ingredient> facade;

    public IngredientFacadeOutAdapter(@Autowired(required = false) CommonEntityFacade<CommonEntities.Ingredient> facade) {
        this.facade = facade;
    }

    @Override
    public List<CommonEntities.Ingredient> loadAll() {
        return facade.loadAll();
    }
}

