package com.bmac.warehouse.ports.in.stock;

public interface ChangeStockUseCase {
    void add(ChangeStockCommand command);

    void remove(ChangeStockCommand command);
}
