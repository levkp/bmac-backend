package com.bmac.store.adapters.in.shell;

import com.bmac.store.ports.in.CancelOrderCommand;
import com.bmac.store.ports.in.CancelOrderUseCase;
import com.bmac.store.ports.in.ForwardBatchCommand;
import com.bmac.store.ports.in.ForwardBatchUseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.LocalDate;
import java.util.UUID;

@Profile("shell")
@ShellComponent
@SuppressWarnings("unused")
public class StoreShell {

    private final ForwardBatchUseCase batchForward;
    private final CancelOrderUseCase cancelOrder;

    @Autowired
    public StoreShell(ForwardBatchUseCase batchForward, CancelOrderUseCase cancelOrder) {
        this.batchForward = batchForward;
        this.cancelOrder = cancelOrder;
    }

    @ShellMethod(key = "forwardLatestBatch", value = "Forward the latest order batch")
    public String forwardLatestBatch() {
        batchForward.forward(new ForwardBatchCommand(LocalDate.now()));
        return "Success";
    }

    @ShellMethod(key = "cancelOrder", value = "Cancel an order by its ID")
    public String cancelOrder(String id) {
        cancelOrder.cancel(new CancelOrderCommand(UUID.fromString(id)));
        return "Success";
    }

    @ShellMethod(value = "Print a message")
    public String print(String message) {
        return message;
    }
}
