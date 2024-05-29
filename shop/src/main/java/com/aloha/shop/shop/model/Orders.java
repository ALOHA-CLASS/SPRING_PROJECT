package com.aloha.shop.shop.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Orders {
    private String id;
    // private Users user;
    private String title;
    private String userId;
    private int totalQuantity = 1;
    private int totalCount = 1;
    private int totalPrice = 0;
    private OrderStatus status;
    private Date orderedAt;
    private Date createdAt;
    private Date updatedAt;

    // params
    private List<String> productId;
    private List<Integer> quantity;

}

