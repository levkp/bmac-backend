package com.bmac.store.adapters.in.shell;

import com.bmac.store.bootstrap.StoreSeeder;
import com.bmac.store.ports.in.batch.ForwardBatchCommand;
import com.bmac.store.ports.in.batch.ForwardBatchUseCase;
import com.bmac.store.ports.in.order.CancelStoreOrderCommand;
import com.bmac.store.ports.in.order.CancelStoreOrderUseCase;
import com.bmac.store.ports.in.product.CreateStoreProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
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
@DependsOn("storeSeeder")
public class StoreShell {

    private final Environment env;
    private final ForwardBatchUseCase batchForward;
    private final CancelStoreOrderUseCase cancelOrder;
    private final CreateStoreProductUseCase createProduct;
    private final StoreSeeder seeder;

    @Autowired
    public StoreShell(Environment env,
                      ForwardBatchUseCase batchForward,
                      CancelStoreOrderUseCase cancelOrder,
                      CreateStoreProductUseCase createProduct,
                      StoreSeeder seeder) {
        this.env = env;
        this.batchForward = batchForward;
        this.cancelOrder = cancelOrder;
        this.createProduct = createProduct;
        this.seeder = seeder;
    }

    @ShellMethod(key = "forwardLatestBatch", value = "Forward the latest order batch")
    public String forwardLatestBatch() {
        batchForward.forward(new ForwardBatchCommand(LocalDate.now()));
        return "Batch forwarded";
    }

    @ShellMethod(key = "cancelOrder", value = "Cancel an order by its ID")
    public String cancelOrder(@ShellOption String id) {
        cancelOrder.cancel(new CancelStoreOrderCommand(UUID.fromString(id)));
        return "Order cancelled";
    }

//    @ShellMethod(key = "createProduct")
//    public void createProduct(@ShellOption String name, @ShellOption double price) {
//        StoreProduct product = createProduct.create(new CreateStoreProductCommand( name, price));
//        System.out.println("Product created with id " + product.getId());
//    }

    @ShellMethod(key = "fakeOrders", value = "Create a given amount of fake orders")
    public String fakeOrders(@ShellOption int amount) {
        seeder.fakeOrders(amount);
        return "Faked " + amount + " orders";
    }

    public Availability fakeOrdersAvailability() {
        return Arrays.asList(env.getActiveProfiles()).contains("dev")
                ? Availability.available()
                : Availability.unavailable("This command can only be used if the dev profile is active");
    }
}
