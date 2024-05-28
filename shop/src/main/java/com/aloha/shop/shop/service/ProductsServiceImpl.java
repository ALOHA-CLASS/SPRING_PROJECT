package com.aloha.shop.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.shop.shop.mapper.ProductsMapper;
import com.aloha.shop.shop.model.Products;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsMapper productsMapper;

    @Override
    public List<Products> list() throws Exception {
        return productsMapper.list();
    }

    @Override
    public Products select(String id) throws Exception {
        return productsMapper.select(id);
    }

    @Override
    public int insert(Products products) throws Exception {
        int result = productsMapper.insert(products);
        return result;
    }

    @Override
    public int update(Products products) throws Exception {
        int result = productsMapper.update(products);
        return result;
    }

    @Override
    public int delete(String deleteIdList) throws Exception {
        int result = productsMapper.delete(deleteIdList);
        return result;
    }
    
}
