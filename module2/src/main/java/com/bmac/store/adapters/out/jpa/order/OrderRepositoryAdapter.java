//package com.bmac.store.adapters.out.db.order;
//
//import com.bmac.store.adapters.out.db.batch.BatchJpaEntity;
//import com.bmac.store.adapters.out.db.batch.BatchRepository;
//import com.bmac.store.domain.Batch;
//import com.bmac.store.domain.Order;
//import com.bmac.store.ports.out.order.OrderActivityCreatePort;
//import com.bmac.store.ports.out.order.OrderLoadPort;
//import com.bmac.store.ports.out.order.OrderReceivePort;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Repository
//public class OrderRepositoryAdapter implements OrderReceivePort, OrderActivityCreatePort, OrderLoadPort {
//
//    private final OrderRepository orderRepository;
//
//    private final OrderActivityRepository orderActivityRepository;
//
//    private final BatchRepository batchRepository;
//
//    public OrderRepositoryAdapter(OrderRepository repository, OrderActivityRepository activityRepository, BatchRepository batchRepository) {
//        this.orderRepository = repository;
//        this.orderActivityRepository = activityRepository;
//        this.batchRepository = batchRepository;
//    }
//
//    @Override
//    public void receive(Order order) {
//        OrderJpaEntity jpaEntity = new OrderJpaEntity();
//        jpaEntity.setUuid(order.getUuid());
//        jpaEntity.setTimestamp(order.getTimestamp());
//        jpaEntity.setBatchUuid(order.getBatch().getUuid());
//        jpaEntity.setProductUuid(order.getProduct().getUuid());
//        jpaEntity.setAmount(order.getAmount());
//
//        orderRepository.save(jpaEntity);
//        createActivity(order.getUuid(), OrderActivityJpaEntity.OrderAction.CREATE);
//    }
//
//    @Override
//    public void createActivity(UUID orderUuid, OrderActivityJpaEntity.OrderAction action) {
//        OrderActivityJpaEntity activity = new OrderActivityJpaEntity();
//        activity.setOrder(orderUuid);
//        activity.setAction(action);
//        orderActivityRepository.save(activity);
//    }
//
//    @Override
//    public List<OrderJpaEntity> loadAllByBatchUuid(UUID uuid) {
//        Optional<BatchJpaEntity> optional = batchRepository.findByUuid(uuid);
//        if (optional.isEmpty()) {
//            return new ArrayList<>();
//        }
//
//        BatchJpaEntity batchJpaEntity = optional.get();
//        Batch batch = new Batch(batchJpaEntity.getUuid(), batchJpaEntity.getDate());
//
//        List<Order> orders = new ArrayList<>();
//        List<OrderJpaEntity> jpaEntities = orderRepository.findAllByBatchUuid(uuid);
//
//
////        jpaEntities.stream().map(jpaEntity -> {
////            new Order(batch, );
////        });
//
//        return new ArrayList<>();
//    }
//}
