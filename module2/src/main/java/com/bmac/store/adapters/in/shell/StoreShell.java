package com.bmac.store.adapters.in.shell;

import com.bmac.store.domain.Product;
import com.bmac.store.ports.in.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDate;
import java.util.UUID;

@Profile("shell")
@ShellComponent
@SuppressWarnings("unused")
public class StoreShell {

    private final ForwardBatchUseCase batchForward;
    private final CancelOrderUseCase cancelOrder;
    private final CreateProductUseCase createProduct;

    @Autowired
    public StoreShell(ForwardBatchUseCase batchForward,
                      CancelOrderUseCase cancelOrder,
                      CreateProductUseCase createProduct) {
        this.batchForward = batchForward;
        this.cancelOrder = cancelOrder;
        this.createProduct = createProduct;
    }

    @ShellMethod(key = "forwardLatestBatch", value = "Forward the latest order batch")
    public void forwardLatestBatch() {
        batchForward.forward(new ForwardBatchCommand(LocalDate.now()));
        System.out.println("Batch forwarded");
    }

    @ShellMethod(key = "cancelOrder", value = "Cancel an order by its ID")
    public void cancelOrder(@ShellOption String id) {
        cancelOrder.cancel(new CancelOrderCommand(UUID.fromString(id)));
        System.out.println("Order cancelled");
    }

    @ShellMethod(key = "createProduct")
    public void createProduct(@ShellOption String name, @ShellOption double price) {
        Product product = createProduct.create(new CreateProductCommand(name, price));
        System.out.println("Product created with id " + product.getId());
    }
}
