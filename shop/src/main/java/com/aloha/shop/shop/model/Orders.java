package com.aloha.shop.shop.model;

import java.util.Date;

import com.aloha.shop.user.model.Users;

import lombok.Data;

@Data
public class Orders {
    private String id;
    private Users user;
    private String title;
    private int totalQuantity = 1;
    private int totalPrice = 0;
    private OrderStatus status;
    private Date orderedAt;
    private Date createdAt;
    private Date updatedAt;
}
