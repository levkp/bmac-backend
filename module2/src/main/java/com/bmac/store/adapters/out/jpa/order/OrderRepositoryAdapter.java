package com.bmac.store.adapters.out.jpa.order;

import com.bmac.store.domain.Order;
import com.bmac.store.domain.Product;
import com.bmac.store.ports.out.batch.BatchLoadPort;
import com.bmac.store.ports.out.product.OrderCreatePort;
import com.bmac.store.ports.out.product.OrderLoadPort;
import com.bmac.store.ports.out.product.ProductLoadPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.*;

@Repository
public class OrderRepositoryAdapter implements OrderCreatePort, OrderLoadPort {

    private final OrderRepository repository;
    private final ProductLoadPort productLoader;
    private final BatchLoadPort batchLoader;

    @Autowired
    public OrderRepositoryAdapter(OrderRepository repository,
                                  ProductLoadPort productLoader,
                                  BatchLoadPort batchLoader) {
        this.repository = repository;
        this.productLoader = productLoader;
        this.batchLoader = batchLoader;
    }

    @Override
    public void create(Order order) {
        HashMap<String, Integer> orderLine = new HashMap<>();
        for(Map.Entry<Product, Integer> item : order.getOrderLine().entrySet()) {
            orderLine.put(item.getKey().getId().toString(), item.getValue());
        }
        OrderJpaEntity jpaEntity = new OrderJpaEntity(
                order.getId(), order.getBatch().getId(), order.getTimestamp(), orderLine);
        repository.save(jpaEntity);
    }

//    @Override
//    @Transactional
//    public List<Order> loadAllByBatchId(UUID batchId) {
//        List<Order> orders = new ArrayList<>();
//        Map<UUID, Product> loadedProducts = new HashMap<>(); // Todo: is this helpful?
//
//        for(OrderJpaEntity jpaEntity : repository.findAllByBatchId(batchId)) {
//
//            Map<Product, Integer> orderLine = new HashMap<>();
//
//            for(Map.Entry<String, Integer> orderLineItem : jpaEntity.getOrderLine().entrySet()) {
//                UUID productId = UUID.fromString(orderLineItem.getKey());
//                Product product;
//
//                if (loadedProducts.containsKey(productId)) {
//                    product = loadedProducts.get(productId);
//                } else {
//                    Optional<Product> optional = productLoader.loadById(productId);
//                    if (optional.isEmpty()) { continue; } // Todo: what happens now?
//                    product = optional.get();
//                    loadedProducts.put(productId, product);
//                }
//
//                orderLine.put(product, orderLineItem.getValue());
//            }
//
//            orders.add(new Order(jpaEntity.getId(), jpaEntity.getTimestamp(), orderLine));
//        }
//
//        return orders;
//    }


    @Override
    @Transactional
    public List<Order> loadAllByBatchId(UUID batchId) {
        return handleQueryResult(repository.findAllByBatchId(batchId));
    }

    @Override
    @Transactional
    public List<Order> loadAllByIds(List<UUID> ids) {
        return handleQueryResult(repository.findAllById(ids));
    }

    private List<Order> handleQueryResult(List<OrderJpaEntity> jpaEntities) {
        List<Order> orders = new ArrayList<>();
        for(OrderJpaEntity jpaEntity : jpaEntities) {
            List<UUID> productIds = jpaEntity.getOrderLine()
                    .keySet()
                    .stream()
                    .map(UUID::fromString)
                    .toList();

            Map<Product, Integer> orderLine = new HashMap<>();
            productLoader.loadAllByIds(productIds).forEach(
                    product -> orderLine.put(product, jpaEntity.getOrderLine().get(product.getId().toString()))
            );
            orders.add(new Order(jpaEntity.getId(), jpaEntity.getTimestamp(), orderLine));
        }
        return orders;
    }

    @Override
    public Optional<Order> loadById(UUID id) {
        Optional<OrderJpaEntity> optional = repository.findById(id);
        if (optional.isEmpty()) return Optional.empty();

        OrderJpaEntity orderJpaEntity = optional.get();

        return batchLoader.loadById(orderJpaEntity.getBatchId()).map(
                batch -> new Order(orderJpaEntity.getId(), batch, orderJpaEntity.getTimestamp())
        );
    }
}
