package com.bmac.warehouse.ports.in.stock;

import com.bmac.warehouse.exception.OutOfStockException;

public interface ChangeStockUseCase {
    void add(ChangeStockCommand command);

    void remove(ChangeStockCommand command) throws OutOfStockException;
}
