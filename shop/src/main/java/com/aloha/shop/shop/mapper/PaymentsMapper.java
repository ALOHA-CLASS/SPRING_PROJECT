package com.aloha.shop.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.shop.shop.model.Payments;

@Mapper
public interface PaymentsMapper {

    public List<Payments> list();

    public Payments select(String id);
    
    public int insert(Payments payments);
    
    public int update(Payments payments);
    
    public int delete(String id);

    // --------------------------------------
    public Payments selectByOrdersId(String ordersId);
}
