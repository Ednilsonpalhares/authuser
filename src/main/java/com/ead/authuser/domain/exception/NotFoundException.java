package com.ead.authuser.domain.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private final Integer status;

    public NotFoundException(final String message, final Integer status) {
        super(message);
        this.status = status;
    }

    public NotFoundException(final String message, final Integer status, final Throwable throwable) {
        super(message, throwable);
        this.status = status;
    }
}
