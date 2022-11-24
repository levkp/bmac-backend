package com.bmac.common.events;

import com.fasterxml.jackson.databind.JsonNode;

public record EventMessage(EventHeader eventHeader, JsonNode messageBody) {

}
