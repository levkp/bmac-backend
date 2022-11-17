package be.kdg.prog6.thebakery.common.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventHeader(UUID uuid, EventType type, LocalDateTime timestamp) {

}
