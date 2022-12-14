package com.bmac.common.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventHeader(UUID id, EventType type, LocalDateTime timestamp) { }
