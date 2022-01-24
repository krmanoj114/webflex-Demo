package com.learning.webflexdemo.config;

import com.learning.webflexdemo.dto.InputFailedValidationResponse;
import com.learning.webflexdemo.exception.InputValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Configuration
public class RouterConfig {
    @Autowired
    RequestHandler requestHandler;

    @Bean
    public RouterFunction<ServerResponse> heighlevelRouter(){
        return RouterFunctions.route()
                .path("test", this::serverResponseRouterFunction)
                .build();
    }

    //@Bean
    public RouterFunction<ServerResponse> serverResponseRouterFunction(){
        return RouterFunctions.route()
                .GET("router/square/{input}", RequestPredicates.path("*/1?"), requestHandler::squareHandler)
                .GET("router/square/{input}", req -> ServerResponse.badRequest().bodyValue("Only 10-19 allowed"))
                .GET("router/table/{input}",  requestHandler::tableHandler)
                .GET("router/tableStream/{input}",  requestHandler::tableStreamHandler)
                .GET("router/multiply",  requestHandler::multiplyHandler)
                .GET("router/square/{input}/validation", requestHandler::squareHandlerWithValidation)
                .onError(InputValidationException.class, exceptionHandler())
                .build();
    }

    private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> exceptionHandler(){
        return (err, req) -> {
            InputValidationException inputValidationException = (InputValidationException) err;
            InputFailedValidationResponse response = new InputFailedValidationResponse();
            response.setInput(inputValidationException.getInput());
            response.setMessage(inputValidationException.getMessage());
            response.setErrorCode(inputValidationException.getErrorCode());
            return ServerResponse.badRequest().bodyValue(response);
        };

    }
}
