package com.bmac.warehouse.core;

import com.bmac.common.domain.CommonEntities;
import com.bmac.warehouse.domain.InboundOrder;
import com.bmac.warehouse.ports.in.order.ReceiveInboundOrderCommand;
import com.bmac.warehouse.ports.in.order.ReceiveInboundOrderUseCase;
import com.bmac.warehouse.ports.out.common.LoadCommonRecipePort;
import com.bmac.warehouse.ports.out.order.CreateOrderPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DefaultReceiveInboundOrderUseCase implements ReceiveInboundOrderUseCase {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final CreateOrderPort orderCreator;
    private final LoadCommonRecipePort commonRecipeLoader;


    @Autowired
    public DefaultReceiveInboundOrderUseCase(CreateOrderPort orderCreator,
                                             LoadCommonRecipePort commonRecipeLoader) {
        this.orderCreator = orderCreator;
        this.commonRecipeLoader = commonRecipeLoader;
    }

    @Override
    public void receive(ReceiveInboundOrderCommand command) {
        log.debug("Creating order for batch " + command.batchId());

        Map<UUID, Double> neededItems = new HashMap<>();
        List<UUID> productIds = command.products().keySet().stream().toList();

        log.trace("Loading CommonRecipes from factory");
        List<CommonEntities.Recipe> recipes = commonRecipeLoader.loadByProductIds(productIds);

        log.trace("Counting amount of needed items");
        for(CommonEntities.Recipe recipe : recipes) {
            recipe.ingredients.entrySet()
                    .stream()
                    .collect(Collectors.toMap(
                            entry -> entry.getKey().id,
                            entry -> entry.getValue() * command.products().get(recipe.product.id)))
                    .forEach((key, value) -> neededItems.merge(key, value, Double::sum));
        }

        log.trace("Creating InboundOrder entity");
        InboundOrder order = new InboundOrder(
                UUID.randomUUID(),
                command.batchId(),
                command.batchDate(),
                LocalDateTime.now(),
                neededItems
        );

        // Todo wtf happens here with the neededItems?
        log.trace("Saving InboundOrder entity");
        orderCreator.create(order);
    }
}
