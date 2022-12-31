package com.bmac.common.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Class entityClass, Class identifierClass, String value) {
        super(String.format("Unable to find %s with %s attribute %s",
                entityClass.getSimpleName(), identifierClass.getSimpleName(), value));
    }
}
