package com.learning.webflexdemo;

import com.learning.webflexdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxStreamTest extends BaseTest {

    @Autowired
    private WebClient webClient;

    @Test
    public void fluxTest(){
        Flux<Response> responseFlux = this.webClient
                .get()
                .uri("reactive-math/multiplication/{input}/stream", 5)
                .retrieve()
                .bodyToFlux(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseFlux)
                .expectNextCount(10)
                .verifyComplete();

    }
}
