package com.aloha.shop.shop.model;

import java.util.Date;

import lombok.Data;

@Data
public class OrderItems {
    private String id;
    private String ordersId;
    private String productsId;
    private int quantity = 1;
    private int price = 0;
    private Integer amount;
    private Date createdAt;
    private Date updatedAt;
}
