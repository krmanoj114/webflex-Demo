package com.learning.webflexdemo.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("jobs")
public class ParamController {

    @GetMapping("search")
    public Flux<Integer> searchJobs(@RequestParam("count") int count,
                                    @RequestParam("page") int page){
        return Flux.just(count, page);

    }

}
