package com.bmac.store.ports.in;

import java.time.LocalDate;

public record ForwardBatchCommand(LocalDate timestamp) { }
