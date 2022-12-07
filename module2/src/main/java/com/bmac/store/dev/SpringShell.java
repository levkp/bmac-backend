package com.bmac.store.dev;

import com.bmac.store.ports.in.batch.ForwardBatchCommand;
import com.bmac.store.ports.in.batch.ForwardBatchUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.LocalDate;

@Profile("dev")
@ShellComponent
public class SpringShell {

    ForwardBatchUseCase batchForward;

    @Autowired
    public SpringShell(ForwardBatchUseCase batchForward) {
        this.batchForward = batchForward;
    }

    @ShellMethod(key = "forwardLatestBatch", value = "Forward the latest order batch")
    public String forwardLatestBatch() {
        batchForward.forward(new ForwardBatchCommand(LocalDate.now()));
        return "Success";
    }

    @ShellMethod(value = "Print a message")
    public String print(String message) {
        return message;
    }
}
