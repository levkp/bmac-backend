package com.bmac.common.cutoff;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DailyCutoffTime {
    private static final LocalTime CUTOFF_TIME = LocalTime.of(22, 0);
    public static final String CUTOFF_CRON = "1 22 * * *";

    public static boolean hasPassed() {
        return CUTOFF_TIME.atDate(LocalDate.now()).isBefore(LocalDateTime.now());
    }

    private DailyCutoffTime() { }
}
