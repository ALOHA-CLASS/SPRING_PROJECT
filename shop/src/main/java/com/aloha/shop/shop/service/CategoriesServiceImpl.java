package com.aloha.shop.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.shop.shop.mapper.CategoriesMapper;
import com.aloha.shop.shop.model.Categories;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoriesMapper categoriesMapper;

    @Override
    public List<Categories> list() throws Exception {
        return categoriesMapper.list();
    }

    @Override
    public Categories select(String id) throws Exception {
        return categoriesMapper.select(id);
    }

    @Override
    public int insert(Categories basic) throws Exception {
        int result = categoriesMapper.insert(basic);
        return result;
    }

    @Override
    public int update(Categories basic) throws Exception {
        int result = categoriesMapper.update(basic);
        return result;
    }

    @Override
    public int delete(String id) throws Exception {
        int result = categoriesMapper.delete(id);
        return result;
    }
    
}
