package com.aloha.sample.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class MainController {

    
    @GetMapping("{page}")
    public String main(@PathVariable("page") String page) {
        return page;
    }
    
    
}
