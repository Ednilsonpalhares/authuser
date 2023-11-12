package com.ead.authuser.communs.configs.feign;

import com.ead.authuser.domain.exception.BusinessException;
import com.ead.authuser.domain.exception.HttpGatewayException;
import com.ead.authuser.domain.exception.NotFoundException;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import feign.codec.ErrorDecoder;

@Slf4j
public class FeignClientErrorDecoderConfig implements ErrorDecoder{

    @Override
    public Exception decode(String methodKey, Response response) {
        log.warn("Verify response error at url {}.", response.request().url());

        Exception exception = new Default().decode(methodKey, response);

        if (response.status() == HttpStatus.NOT_FOUND.value()) {
            log.warn("Resource not found.");

            throw new NotFoundException("Resource not found", HttpStatus.NOT_FOUND.value());
        }

        if (response.status() >= 400 && response.status() <= 499) {
            log.warn("Failed status code: {}", response.status());

      throw new BusinessException(exception.getMessage(), response.status());
        }

        if (response.status() >= 500 && response.status() <= 599) {
            log.warn("Service unavailable: {}", response.status());
            throw new HttpGatewayException(exception.getMessage(), response.status());
        }

        return new Default().decode(methodKey, response);
    }
}
