package com.thoughtmechanicx.licensingservice.utils;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

@Slf4j
public class UserContextInterceptor {

    @Bean
    public RequestInterceptor requestInterceptor() {
        log.info("***Entered in the licensing interceptor *********");
        log.info("Auth token::  " + UserContextHolder.getContext().getAuthToken());
        return (template) -> template
                .header(UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId())
                .header(UserContext.AUTH_TOKEN, UserContextHolder.getContext().getAuthToken());
    }
}

//Below is the impl when we use RestTemplate as our cleint
//public class UserContextInterceptor implements ClientHttpRequestInterceptor {
//
//
//    @Override
//    public ClientHttpResponse intercept(
//            HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
//            throws IOException {
//        log.info("***Entered in the licensing interceptor *********");
//
//        HttpHeaders headers = request.getHeaders();
//        headers.add(UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId());
//        headers.add(UserContext.AUTH_TOKEN, UserContextHolder.getContext().getAuthToken());
//
//        return execution.execute(request, body);
//    }
//}