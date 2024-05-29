package com.aloha.shop.shop.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller("orders")
@RequestMapping("/orders")
public class OrdersController {

    /**
     * 주문하기
     * @param param
     * @return
     */
    @GetMapping("")
    public String orders() {


        return "/orders/index";
    }

    /**
     * 주문 등록
     * 1. product - id, quantity
     * 2. 
     * @param entity
     * @return
     */
    @PostMapping("")
    public String orderPost(@RequestParam Map<String, String> map) {
        String productId = map.get("productId");
        String quantity = map.get("quantity");

        log.info("productId : " + productId);
        log.info("quantity : " + quantity);
        return "/orders/index";
    }
    
    
    
}
