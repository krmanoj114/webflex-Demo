package com.learning.webflexdemo;

import com.learning.webflexdemo.dto.InputFailedValidationResponse;
import com.learning.webflexdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ExchangeTest extends BaseTest{
    @Autowired
    private WebClient webClient;
   // exchange = response + addition info on http status
    @Test
    public void badRequestTest(){
        Mono<Object> objectMono = this.webClient
                .get()
                .uri("reactive-math/squire/{input}/throw", 5)
                .exchangeToMono(this::exchange)
                .doOnNext(System.out::println)
                .doOnError(err -> System.out.println(err.fillInStackTrace()));

        StepVerifier.create(objectMono)
                .expectNextCount(1)
                .verifyComplete();

    }

    private  Mono<Object> exchange(ClientResponse clientResponse) {
        if(clientResponse.rawStatusCode() == 400)
            return clientResponse.bodyToMono(InputFailedValidationResponse.class);
        else
            return clientResponse.bodyToMono(Response.class);
    }

}
