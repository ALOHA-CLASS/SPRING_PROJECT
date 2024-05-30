package com.aloha.shop.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.shop.user.mapper.AddressMapper;
import com.aloha.shop.user.model.Address;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> list() throws Exception {
        return addressMapper.list();
    }

    @Override
    public Address select(String id) throws Exception {
        return addressMapper.select(id);
    }

    @Override
    public int insert(Address address) throws Exception {
        int result = addressMapper.insert(address);
        return result;
    }

    @Override
    public int update(Address address) throws Exception {
        int result = addressMapper.update(address);
        return result;
    }

    @Override
    public int delete(String id) throws Exception {
        int result = addressMapper.delete(id);
        return result;
    }

    @Override
    public List<Address> listByUserId(String userId) throws Exception {
        return addressMapper.listByUserId(userId);
    }
}