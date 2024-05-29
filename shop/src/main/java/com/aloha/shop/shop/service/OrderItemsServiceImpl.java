package com.aloha.shop.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.shop.shop.mapper.OrderItemsMapper;
import com.aloha.shop.shop.model.OrderItems;

@Service
public class OrderItemsServiceImpl implements OrderItemsService {

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Override
    public List<OrderItems> list() throws Exception {
        return orderItemsMapper.list();
    }

    @Override
    public OrderItems select(String id) throws Exception {
        return orderItemsMapper.select(id);
    }

    @Override
    public int insert(OrderItems orderItems) throws Exception {
        int result = orderItemsMapper.insert(orderItems);
        return result;
    }

    @Override
    public int update(OrderItems orderItems) throws Exception {
        int result = orderItemsMapper.update(orderItems);
        return result;
    }

    @Override
    public int delete(String id) throws Exception {
        int result = orderItemsMapper.delete(id);
        return result;
    }
    
}
