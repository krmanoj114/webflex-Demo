package com.learning.webflexdemo;

import com.learning.webflexdemo.dto.MultiplyRequestDto;
import com.learning.webflexdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class CalculatorTest extends BaseTest{

    @Autowired
    private WebClient webClient;

    private static final String FORMAT ="%d, %s, %d = %s";
    private static final int A =10;

    @Test
    public void headerTest(){
        Flux<String> stringFlux = Flux.range(1, 5)
                .flatMap(b -> Flux.just("+", "-", "*", "/")
                        .flatMap(op -> send(b, op)))
                .doOnNext(System.out::println);


        StepVerifier.create(stringFlux)
                .expectNextCount(20)
                .verifyComplete();

    }

    private Mono<String> send(int b, String op){
        return this.webClient
                .get()
                .uri("calculator/{a}/{b}", A, b)
                .headers(h -> h.set("OP", op))
                .retrieve()
                .bodyToMono(Response.class)
                .map(v -> String.format(FORMAT, A, op, b, v));
    }

}
