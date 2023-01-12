package com.bmac.store.bootstrap;

import com.bmac.store.domain.StoreProduct;
import com.bmac.store.ports.in.order.ReceiveStoreOrderCommand;
import com.bmac.store.ports.in.order.ReceiveStoreOrderUseCase;
import com.bmac.store.ports.in.product.CreateStoreProductCommand;
import com.bmac.store.ports.in.product.CreateStoreProductUseCase;
import com.bmac.store.ports.out.product.LoadCommonProductPort;
import com.bmac.store.ports.out.product.LoadStoreProductPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Order(2)
public class StoreSeeder implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final Environment env;
    private final LoadCommonProductPort commonProductLoader;
    private final LoadStoreProductPort productLoader;
    private final CreateStoreProductUseCase createProduct;
    private final ReceiveStoreOrderUseCase receiveOrder;

    public StoreSeeder(Environment env,
                       LoadCommonProductPort commonProductLoader,
                       LoadStoreProductPort productLoader,
                       CreateStoreProductUseCase createProduct,
                       ReceiveStoreOrderUseCase receiveOrder) {
        this.env = env;
        this.commonProductLoader = commonProductLoader;
        this.productLoader = productLoader;
        this.createProduct = createProduct;
        this.receiveOrder = receiveOrder;
    }

    @Override
    public void run(String... args) {
        if (Arrays.asList(env.getActiveProfiles()).contains("seed")) {
            syncProducts();
        }
        fakeOrders(20);
    }

    private void syncProducts() {
        log.info("Syncing products from factory");
        Random random = new Random();
        commonProductLoader.loadAll().forEach(commonProduct -> {
            createProduct.create(new CreateStoreProductCommand(commonProduct.id, commonProduct.name, random.nextInt(999) / 10.0));
        });
    }

    public void fakeOrders(int amount) {
        log.info("Faking " + amount + " orders");

        List<UUID> productIds = productLoader.loadAll().stream().map(StoreProduct::getId).toList();
        Random random = new Random();

        // Creating 20 fake orders
        for(int i = 0; i < amount; i++) {
            Map<UUID, Integer> orderLine = new HashMap<>();
            // Let's say one order can contain between 1 and 10 products
            for(int j = 0; j < random.nextInt(1, 10); j++) {
                // Let's say one item can be ordered between 1 and 15 times at once
                orderLine.put(productIds.get(random.nextInt(productIds.size())), random.nextInt(1, 15));
            }
            receiveOrder.receive(new ReceiveStoreOrderCommand(orderLine));
        }
    }
}
