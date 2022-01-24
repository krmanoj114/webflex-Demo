package com.learning.webflexdemo;

import com.learning.webflexdemo.dto.MultiplyRequestDto;
import com.learning.webflexdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class BadRequestTest extends BaseTest{
    @Autowired
    private WebClient webClient;

    @Test
    public void badRequestTest(){
        Mono<Response> responseMono = this.webClient
                .get()
                .uri("reactive-math/squire/{input}/throw", 5)
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println)
                .doOnError(err -> System.out.println(err.fillInStackTrace()));

        StepVerifier.create(responseMono)
                .verifyError(WebClientResponseException.BadRequest.class);

    }

}
