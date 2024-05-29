package com.aloha.shop.shop.model;

import java.util.Date;

import lombok.Data;

@Data
public class Categories {

    private String id;
    private String code;
    private String name;
    private Date createdAt;
    private Date updatedAt;
    private int seq;
    
}
