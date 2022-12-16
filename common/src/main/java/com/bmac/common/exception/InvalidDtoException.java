package com.bmac.common.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

public class InvalidDtoException extends RuntimeException{
    private final List<String> messages;

    public InvalidDtoException(BindingResult br) {
        messages = br.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }

    public List<String> getMessages() {
        return messages;
    }
}
