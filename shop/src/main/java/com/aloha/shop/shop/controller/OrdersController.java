package com.aloha.shop.shop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aloha.shop.shop.model.Orders;
import com.aloha.shop.shop.service.OrdersService;
import com.aloha.shop.user.model.Users;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller("orders")
@RequestMapping("/orders")
public class OrdersController {


    @Autowired
    private OrdersService ordersService;


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
     * @throws Exception 
     */
    @PostMapping("")
    public String orderPost(Orders orders
                           ,HttpSession session
                           ,@RequestParam List<String> productId
                           ,@RequestParam List<Integer> quantity) throws Exception {
        
        Users user = (Users) session.getAttribute("user");
        orders.setUserId(user.getId());
        orders.setProductId(productId);
        orders.setQuantity(quantity);

        int result = ordersService.insert(orders);
        log.info("productId : " + productId.get(0));
        log.info("quantity : " + quantity.get(0));
        return "/orders/index";
    }
    
    
    
}
