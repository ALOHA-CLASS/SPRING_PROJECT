package com.aloha.pay_toss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

    /**
     * 메인 화면
     * @return
     */
    @GetMapping(value={"/" ,""})
    public String home() {
        return "/index";
    }

    /**
     * 결제 성공
     * @param result
     * @param productId
     * @return
     */
    @GetMapping("/success")
    public String success(@RequestParam("orderId") String orderId) {
        log.info("결제 성공!!!");
        log.info("orderId : " + orderId);
        return "/success";
    }

    /**
     * 결제 실패
     * @param result
     * @param productId
     * @return
     */
    @GetMapping("/fail")
    public String fail(@RequestParam("orderId") String orderId) {
        log.info("결제 실패!!!");
        log.info("orderId : " + orderId);
        return "/fail";
    }
    
    
}