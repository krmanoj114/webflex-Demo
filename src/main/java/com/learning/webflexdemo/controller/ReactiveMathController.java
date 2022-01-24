package com.learning.webflexdemo.controller;

import com.learning.webflexdemo.dto.MultiplyRequestDto;
import com.learning.webflexdemo.dto.Response;
import com.learning.webflexdemo.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathController {
    @Autowired
    ReactiveMathService service;

    @GetMapping("squire/{input}")
    public Mono<Response> findSquire(@PathVariable int input ){
        return this.service.findSquare(input);
    }

    @GetMapping("multiplication/{input}")
    public Flux<Response> multiplicationTable(@PathVariable int input){
        return this.service.multiplicationTable(input);
    }

    @GetMapping(value="multiplication/{input}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> multiplicationTableStream(@PathVariable int input){
        return this.service.multiplicationTable(input);
    }

    @PostMapping("multiply")
    public Mono<Response> multiply(@RequestBody Mono<MultiplyRequestDto> multiplyRequestDtoMono,
                                   @RequestHeader Map<String, String> header){
             System.out.println(header);
             return this.service.multiply(multiplyRequestDtoMono);
    }

}
