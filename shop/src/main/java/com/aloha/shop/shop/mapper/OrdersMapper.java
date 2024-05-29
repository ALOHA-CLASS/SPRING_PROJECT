package com.aloha.shop.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.shop.shop.model.Orders;

@Mapper
public interface OrdersMapper {

    public List<Orders> list() throws Exception;

    public Orders select(String id) throws Exception;

    public int insert(Orders ordere) throws Exception;

    public int update(Orders ordere) throws Exception;

    public int delete(String id) throws Exception;
    
}
