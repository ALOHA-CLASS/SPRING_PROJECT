package com.aloha.shop.shop.service;

import java.util.List;

import com.aloha.shop.shop.model.Categories;

public interface CategoriesService {
    
    public List<Categories> list() throws Exception;

    public Categories select(String id) throws Exception;

    public int insert(Categories basic) throws Exception;

    public int update(Categories basic) throws Exception;

    public int delete(String id) throws Exception;
}
