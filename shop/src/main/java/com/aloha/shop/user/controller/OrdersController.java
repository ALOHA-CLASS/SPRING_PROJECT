package com.aloha.shop.user.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.shop.shop.model.Orders;
import com.aloha.shop.shop.service.OrdersService;
import com.aloha.shop.user.model.Users;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("userOrders")
@RequestMapping("/user/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    /**
     * 주문 내역
     * @return
     * @throws Exception 
     */
    @GetMapping("")
    public String userOrders(Model model
                           ,HttpSession session) throws Exception {
        log.info("::::::::::::::::::::: 주문 내역 - user :::::::::::::::::::::}");
        Users user = (Users) session.getAttribute("user");
        String userId = user.getId();
        List<Orders> orders = ordersService.listByUserId(userId);
        model.addAttribute("orders", orders);
        
        return "/user/orders/index";
    }

    
}
