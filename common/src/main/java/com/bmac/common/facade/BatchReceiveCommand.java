package com.bmac.common.facade;

import com.bmac.common.events.BatchForwardedEvent;

public record BatchReceiveCommand(BatchForwardedEvent event) { }
