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
        // 기본 배송지 처리
        if( address.getIsDefault() ) {
            List<Address> addressList = addressMapper.listByUserId(address.getUserId());
            for (Address addr : addressList) {
                // ❌ 이전 기존 배송지 해제
                if( addr.getIsDefault() ) {
                    addr.setIsDefault(false);
                    update(addr);
                }
            }
        }
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