package com.learning.webflexdemo;

import com.learning.webflexdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class getSingleResponseTest extends BaseTest {
    @Autowired
    private WebClient webClient;

    @Test
    public void blockTest(){
        Response block = this.webClient
                .get()
                .uri("reactive-math/squire/{input}", 5)
                .retrieve()
                .bodyToMono(Response.class)
                .block();
        System.out.println("block = " + block);
        
    }

    @Test
    public void stepVerifierTest(){
        Mono<Response> responseMono = this.webClient
                .get()
                .uri("reactive-math/squire/{input}", 5)
                .retrieve()
                .bodyToMono(Response.class);

        StepVerifier.create(responseMono)
                .expectNextMatches(response -> response.getOutput() == 25)
                .verifyComplete();



        //System.out.println("responseMono = " + responseMono);

    }

}
