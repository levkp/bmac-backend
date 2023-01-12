package com.bmac.store.ports.in.batch;

import java.time.LocalDate;

public record ForwardBatchCommand(LocalDate timestamp) { }
