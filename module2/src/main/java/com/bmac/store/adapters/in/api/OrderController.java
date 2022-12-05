package com.bmac.store.adapters.in.api;

import com.bmac.store.adapters.in.api.dto.OrderDto;
import com.bmac.store.adapters.in.api.dto.OrderItemDto;
import com.bmac.store.core.exception.StoreEntityNotFoundException;
import com.bmac.store.ports.in.order.ReceiveOrderCommand;
import com.bmac.store.ports.in.order.ReceiveOrderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class OrderController {
    private final ReceiveOrderUseCase receiveOrder;

    @Autowired
    public OrderController(ReceiveOrderUseCase receiveOrder) {
        this.receiveOrder = receiveOrder;
    }

    @PostMapping("/api/orders/")
    public ResponseEntity<Void> receive(@RequestBody OrderDto dto) {
        // Todo: validation of dto? what exception is thrown?
        Map<UUID, Integer> orderLine = dto.getOrderLine()
                .stream()
                .collect(Collectors.toMap(OrderItemDto::getProductId, OrderItemDto::getAmount));
        receiveOrder.receive(new ReceiveOrderCommand(orderLine));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleStoreEntityNotFoundException(StoreEntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
