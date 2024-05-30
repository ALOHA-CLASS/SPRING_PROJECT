package com.aloha.shop.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.shop.shop.model.Shipments;

@Mapper
public interface ShipmentsMapper {

    public List<Shipments> list();

    public Shipments select(String id);
    
    public int insert(Shipments shipment);
    
    public int update(Shipments shipment);
    
    public int delete(String id);

    public Shipments selectByOrdersId(String orderId);
}