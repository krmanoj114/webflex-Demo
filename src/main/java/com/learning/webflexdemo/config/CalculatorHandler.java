package com.learning.webflexdemo.config;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Service
public class CalculatorHandler {
    public Mono<ServerResponse> additionHandler(ServerRequest req){
        return process(req,(a, b)->ServerResponse.ok().bodyValue(a + b));
    }

    public Mono<ServerResponse> substractHandler(ServerRequest req){
        return process(req, (a, b) -> ServerResponse.ok().bodyValue(a -b));
    }

    public Mono<ServerResponse> multiplicationHandler(ServerRequest req){
        return process(req, (a, b) -> ServerResponse.ok().bodyValue(a * b));
    }

    public Mono<ServerResponse> divisionHandler(ServerRequest req){
        return process(req, (a, b) ->
                b !=0 ? ServerResponse.ok().bodyValue(a / b):
                        ServerResponse.ok().bodyValue(a / b));
    }

    private Mono<ServerResponse> process(ServerRequest req, BiFunction<Integer, Integer, Mono<ServerResponse>> operationlogic){
        int a = getValue(req, "a");
        int b = getValue(req, "b");
        return operationlogic.apply(a, b);
    }

    private int getValue(ServerRequest req, String key) {
        return Integer.parseInt(req.pathVariable(key));
    }
}
