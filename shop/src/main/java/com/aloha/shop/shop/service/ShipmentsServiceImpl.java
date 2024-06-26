package com.aloha.shop.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.shop.shop.mapper.ShipmentsMapper;
import com.aloha.shop.shop.model.Shipments;
import com.aloha.shop.user.model.Address;
import com.aloha.shop.user.service.AddressService;

@Service
public class ShipmentsServiceImpl implements ShipmentsService {

    @Autowired
    private AddressService addressService;

    @Autowired
    private ShipmentsMapper shipmentsMapper;

    @Override
    public List<Shipments> list() throws Exception {
        return shipmentsMapper.list();
    }

    @Override
    public Shipments select(String id) throws Exception {
        Shipments shipments = shipmentsMapper.select(id);
        String addressId = shipments.getAddressId();
        Address address = addressService.select(addressId);
        shipments.setAddress(address);
        return shipments;
    }

    @Override
    public int insert(Shipments shipments) throws Exception {
        return shipmentsMapper.insert(shipments);
    }

    @Override
    public int update(Shipments shipments) throws Exception {
        return shipmentsMapper.update(shipments);
    }

    @Override
    public int delete(String id) throws Exception {
        return shipmentsMapper.delete(id);
    }

    @Override
    public Shipments selectByOrdersId(String orderId) throws Exception {
        Shipments shipments = shipmentsMapper.selectByOrdersId(orderId);
        String addressId = shipments.getAddressId();
        Address address = addressService.select(addressId);
        shipments.setAddress(address);
        return shipments;
    }
}