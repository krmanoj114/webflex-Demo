package com.learning.webflexdemo.controller;

import com.learning.webflexdemo.dto.Response;
import com.learning.webflexdemo.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("math")
public class MathController {
    @Autowired
    MathService service;

    @GetMapping("squire/{input}")
    public Response findSquire(@PathVariable int input ){
        return this.service.findSquare(input);
    }

    @GetMapping("multiplication/{input}")
    public List<Response> multiplicationTable(@PathVariable int input){
        return this.service.multiplicationTable(input);
    }

}
