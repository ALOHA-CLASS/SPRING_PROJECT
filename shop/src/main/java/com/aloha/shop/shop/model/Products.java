package com.aloha.shop.shop.model;

import java.util.Date;

import lombok.Data;

@Data
public class Products {
    private String id;
    private String name;
    private String category;
    private String content;
    private int price;
    private int stock = 0;
    private Date createdAt;
    private Date updatedAt;
}
