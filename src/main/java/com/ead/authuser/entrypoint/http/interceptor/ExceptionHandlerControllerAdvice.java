package com.ead.authuser.entrypoint.http.interceptor;

import com.ead.authuser.domain.exception.BusinessException;
import com.ead.authuser.entrypoint.http.commons.dto.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    private static final String BUSINESS_EXCEPTION = "Business exception: ";

    @ExceptionHandler({BusinessException.class})
    public @ResponseBody ResponseEntity<ErrorDTO> handleBusinessException(final BusinessException exception) {
        log.error(BUSINESS_EXCEPTION, exception);

        return new ResponseEntity<>(ErrorDTO.builder()
                .code(exception.getStatus())
                .message(exception.getMessage()).build(), HttpStatusCode.valueOf(exception.getStatus()));
    }
}
