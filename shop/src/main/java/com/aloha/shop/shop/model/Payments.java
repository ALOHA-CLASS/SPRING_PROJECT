package com.aloha.shop.shop.model;

import java.util.Date;

import lombok.Data;

@Data
public class Payments {
    private String id;
    private Orders order;
    private String paymentMethod;
    private String status;
    private Date paidAt;
    private Date createdAt;
    private Date updatedAt;
}
