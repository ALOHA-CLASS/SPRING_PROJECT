package com.aloha.shop.shop.model;

import java.util.Date;

import com.aloha.shop.user.model.Address;

import lombok.Data;

@Data
public class Shipments {
    private String id;
    private String orderId;
    private String addressId;
    private String trackingNo;
    private String shipCompany;
    private ShipmentsStatus status;
    private Date createdAt;
    private Date updatedAt;
    // ------------------------------------------------
    private Address address;
}