package com.bmac.warehouse.exception;

public class WarehouseEntityNotFoundException extends RuntimeException {
    public WarehouseEntityNotFoundException(Class entityClass, Class identifierClass, String value) {
        super(String.format("Unable to find %s with %s attribute %s",
                entityClass.getSimpleName(), identifierClass.getSimpleName(), value));
    }

    public WarehouseEntityNotFoundException(Class entityClass, String id) {
        super(String.format("Unable to find %s with id %s", entityClass.getSimpleName(), id));
    }
}
