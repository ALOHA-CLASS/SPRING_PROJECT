package com.aloha.shop.shop.model;

import java.util.Date;

import com.aloha.shop.user.model.Users;

import lombok.Data;

@Data
public class Carts {
    private String id;
    private Products product;
    private Users user;
    private int amount = 1;
    private Date createdAt;
    private Date updatedAt;
}
