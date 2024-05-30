package com.aloha.shop.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("userOrders")
@RequestMapping("/user/orders")
public class OrdersController {

    /**
     * 주문 내역
     * @return
     */
    @GetMapping("")
    public String userOrders() {
        log.info("::::::::::::::::::::: 주문 내역 - user :::::::::::::::::::::}");
        
        return "/user/orders/index";
    }

    
}
