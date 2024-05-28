package com.aloha.shop.admin.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.shop.shop.model.Products;
import com.aloha.shop.shop.service.ProductsService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





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
