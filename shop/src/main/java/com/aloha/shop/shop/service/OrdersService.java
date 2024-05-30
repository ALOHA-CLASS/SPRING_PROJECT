package com.aloha.shop.shop.service;

import java.util.List;

import com.aloha.shop.shop.model.Orders;

public interface OrdersService {

    public List<Orders> list() throws Exception;

    public Orders select(String id) throws Exception;

    public int insert(Orders orders) throws Exception;

    public int update(Orders orders) throws Exception;

    public int delete(String id) throws Exception;

    // ---------------------------------------------------------
    public List<Orders> listByUserId(String userId) throws Exception;
    
}
