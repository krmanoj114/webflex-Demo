package com.learning.webflexdemo.controller;

import com.learning.webflexdemo.dto.Response;
import com.learning.webflexdemo.exception.InputValidationException;
import com.learning.webflexdemo.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathValidationController {

    @Autowired
    ReactiveMathService service;

    @GetMapping("squire/{input}/throw")
    public Mono<Response> findSquire(@PathVariable int input ){
        if(input < 10 || input > 20) {
            throw new InputValidationException(input);
        }
        return this.service.findSquare(input);
    }

    @GetMapping("squire/{input}/mono-error")
    public Mono<Response> monoError(@PathVariable int input ){
        return Mono.just(input)
                .handle((integer, sink) -> {
                    if(integer >= 10 && integer <= 20) {
                        sink.next(integer);
                    }else{
                        sink.error(new InputValidationException(integer));
                    }
                })
                .cast(Integer.class)
                .flatMap(i -> this.service.findSquare(i));
    }

    @GetMapping("squire/{input}/assignment")
    public Mono<ResponseEntity<Response>> assignment(@PathVariable int input ){
        return Mono.just(input)
                .filter(i -> i >10 && i < 20)
                .flatMap(i -> this.service.findSquare(i))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
