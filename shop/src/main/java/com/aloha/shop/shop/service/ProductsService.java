package com.aloha.shop.shop.service;

import java.util.List;

import com.aloha.shop.shop.model.Products;

public interface ProductsService {
    public List<Products> list() throws Exception;

    public Products select(String id) throws Exception;

    public int insert(Products prodcuts) throws Exception;

    public int update(Products prodcuts) throws Exception;

    public int delete(String deleteIdList) throws Exception;
}
