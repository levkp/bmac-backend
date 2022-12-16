package com.bmac.warehouse.ports.in;

import com.bmac.warehouse.domain.Item;
import com.bmac.warehouse.domain.Temperature;

public record CreateItemCommand(String name, double minimum, double maximum, Temperature temperature, Item.Unit unit) {

}
