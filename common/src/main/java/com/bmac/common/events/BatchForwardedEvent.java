package com.bmac.common.events;

import java.time.LocalDate;
import java.util.UUID;

public record BatchForwardedEvent(UUID batchId, LocalDate batchDate) {

}
