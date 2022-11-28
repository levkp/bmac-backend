package com.bmac.store.ports.out.batch;

import java.util.UUID;

public interface BatchForwardPort {
    void forward(UUID uuid);
}
