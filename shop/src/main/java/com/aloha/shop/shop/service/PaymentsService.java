package com.aloha.shop.shop.service;

import java.util.List;

import com.aloha.shop.shop.model.Payments;

public interface PaymentsService {

    public List<Payments> list() throws Exception;

    public Payments select(String id) throws Exception;

    public int insert(Payments payments) throws Exception;

    public int update(Payments payments) throws Exception;

    public int delete(String id) throws Exception;

    // --------------------------------------
    public Payments selectByOrdersId(String ordersId);
}