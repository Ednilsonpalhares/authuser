package com.ead.authuser.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class HttpGatewayException extends RuntimeException {

    private final int statusCode;

    public HttpGatewayException(final String message, final Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public HttpGatewayException(final String message, final Integer statusCode, final Throwable error) {
        super(message, error);
        this.statusCode = statusCode;
    }
}
