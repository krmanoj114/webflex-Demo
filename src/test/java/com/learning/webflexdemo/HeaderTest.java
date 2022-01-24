package com.learning.webflexdemo;

import com.learning.webflexdemo.dto.MultiplyRequestDto;
import com.learning.webflexdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class HeaderTest extends BaseTest {

    @Autowired
    private WebClient webClient;

    @Test
    public void headerTest(){
        Mono<Response> responseMono = this.webClient
                .post()
                .uri("reactive-math/multiply", 5)
                .bodyValue(buildRequestDto(5, 2))
                .headers(h -> h.set("someKey", "someValue"))
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseMono)
                .expectNextCount(1)
                .verifyComplete();

    }
    private MultiplyRequestDto buildRequestDto(int i, int i1){
        MultiplyRequestDto dto = new MultiplyRequestDto();
        dto.setFirst(i);
        dto.setSecond(i1);
        return dto;
    }
}
