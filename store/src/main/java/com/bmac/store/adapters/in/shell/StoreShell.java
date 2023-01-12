package com.bmac.store.adapters.in.shell;

import com.bmac.store.ports.in.batch.ForwardBatchCommand;
import com.bmac.store.ports.in.batch.ForwardBatchUseCase;
import com.bmac.store.ports.in.order.CancelStoreOrderCommand;
import com.bmac.store.ports.in.order.CancelStoreOrderUseCase;
import com.bmac.store.ports.in.product.CreateStoreProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

@Profile("shell")
@ShellComponent
@SuppressWarnings("unused")
public class StoreShell {

    private final Environment env;
    private final ForwardBatchUseCase batchForward;
    private final CancelStoreOrderUseCase cancelOrder;
    private final CreateStoreProductUseCase createProduct;

    @Autowired
    public StoreShell(Environment env,
                      ForwardBatchUseCase batchForward,
                      CancelStoreOrderUseCase cancelOrder,
                      CreateStoreProductUseCase createProduct) {
        this.env = env;
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
        cancelOrder.cancel(new CancelStoreOrderCommand(UUID.fromString(id)));
        System.out.println("Order cancelled");
    }

//    @ShellMethod(key = "createProduct")
//    public void createProduct(@ShellOption String name, @ShellOption double price) {
//        StoreProduct product = createProduct.create(new CreateStoreProductCommand( name, price));
//        System.out.println("Product created with id " + product.getId());
//    }

    @ShellMethod(key = "fakeOrders")
    public void fakeOrders(@ShellOption int amount) {
        System.out.println(amount);
    }

    public Availability fakeOrdersAvailability() {
        return Arrays.asList(env.getActiveProfiles()).contains("dev")
                ? Availability.available()
                : Availability.unavailable("This command can only be used if the dev profile is active");
    }
}
