package com.bmac.store.adapters.in.api;

import com.bmac.common.exception.InvalidDtoException;
import com.bmac.store.adapters.in.api.dto.OrderDto;
import com.bmac.store.adapters.in.api.dto.OrderItemDto;
import com.bmac.store.core.exception.StoreEntityNotFoundException;
import com.bmac.store.ports.in.order.ReceiveOrderCommand;
import com.bmac.store.ports.in.order.ReceiveOrderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class OrderController {
    private final ReceiveOrderUseCase receiveOrder;

    @Autowired
    public OrderController(ReceiveOrderUseCase receiveOrder) {
        this.receiveOrder = receiveOrder;
    }

    @PostMapping("/api/orders")
    public ResponseEntity<Void> receive(@Valid @RequestBody OrderDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new InvalidDtoException(bindingResult); // Todo: not working
        // This mapping is added because I don't know if it's possible to parse the request body straight to maps
        // Lists work for sure
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

    @ExceptionHandler
    public ResponseEntity<List<String>> handleInvalidDtoException(InvalidDtoException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessages());
    }
}
