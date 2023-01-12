package com.bmac.warehouse.adapters.out.facade;

import com.bmac.common.domain.CommonEntities;
import com.bmac.common.facade.CommonEntityFacade;
import com.bmac.warehouse.ports.out.common.LoadCommonRecipePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class RecipeFacadeOutAdapter implements LoadCommonRecipePort {

    private final CommonEntityFacade<CommonEntities.Recipe> facade;

    @Autowired
    public RecipeFacadeOutAdapter(@Autowired(required = false) CommonEntityFacade<CommonEntities.Recipe> facade) {
        this.facade = facade;
    }

    @Override
    public List<CommonEntities.Recipe> loadByProductIds(List<UUID> productIds) {
        return facade.loadAll()
                .stream()
                .filter(recipe -> productIds.contains(recipe.product.id))
                .toList();
    }
}
