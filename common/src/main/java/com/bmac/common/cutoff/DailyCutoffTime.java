package com.bmac.common.cutoff;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DailyCutoffTime {
    private static final LocalTime TIME = LocalTime.of(22, 0);
    public static final String CRON = "1 22 * * *";

    public static LocalDateTime ofToday() {
        return TIME.atDate(LocalDate.now());
    }
    public static boolean hasPassed() {
        return ofToday().isBefore(LocalDateTime.now());
    }

    private DailyCutoffTime() { }
}
