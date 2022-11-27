package com.bmac.store.adapters.in.api;

import com.bmac.store.core.exception.ProductNotFoundException;
import com.bmac.store.ports.in.ReceiveOrderCommandMVP;
import com.bmac.store.ports.in.ReceiveOrderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.UUID;

@RestController
public class OrderController {
    private final ReceiveOrderUseCase receiveOrder;

    @Autowired
    public OrderController(ReceiveOrderUseCase receiveOrder) {
        this.receiveOrder = receiveOrder;
    }

    // Todo: MVP only: path variables in URL instead of request body
    @PostMapping("/api/orders/{productId}/{amount}")
    public ResponseEntity<Void> receiveOrder(@PathVariable UUID productId, @PathVariable int amount) {
        receiveOrder.receive(new ReceiveOrderCommandMVP(productId, amount));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
