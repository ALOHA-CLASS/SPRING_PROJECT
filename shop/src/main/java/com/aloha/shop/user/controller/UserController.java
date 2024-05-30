package com.aloha.shop.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 마이 페이지
     * @return
     */
    @GetMapping("")
    public String userHome() {
        log.info("::::::::::::::::::::: 마이 페이지 - user :::::::::::::::::::::}");
        
        return "/user/index";
    }


    /**
     * 회원 정보
     * @return
     */
    @GetMapping("/info")
    public String userInfo() {
        
        return "/user/info";
    }
    

    
    
}
