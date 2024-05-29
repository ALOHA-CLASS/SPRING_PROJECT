package com.aloha.shop.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.shop.shop.model.Products;
import com.aloha.shop.shop.service.ProductsService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller("products")
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("")
    public String products(Model model) throws Exception {
        log.info(":::::::::: 상품 ::::::::::");
        List<Products> products = productsService.list(); 
        log.info(":::::::::: products ::::::::::");
        log.info("products : " + products);
        model.addAttribute("products", products);

        return "/products/index";
    }
    
    
}
