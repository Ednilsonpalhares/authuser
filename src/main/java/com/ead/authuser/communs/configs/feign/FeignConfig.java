package com.ead.authuser.communs.configs.feign;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class FeignConfig {

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder().errorDecoder(new FeignClientErrorDecoderConfig());
    }
}
