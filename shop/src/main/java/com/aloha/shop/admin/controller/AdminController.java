package com.aloha.shop.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;





@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    /**
     * 관리자 메인
     * @return
     */
    @GetMapping("")
    public String adminHome() {
        log.info("::::: admin :::::");
        return "/admin/index";
    }

    
    
    
    
}
