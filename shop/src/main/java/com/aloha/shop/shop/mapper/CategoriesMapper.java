package com.aloha.shop.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.shop.shop.model.Categories;


@Mapper
public interface CategoriesMapper {

    public List<Categories> list() throws Exception;

    public Categories select(String id) throws Exception;

    public int insert(Categories basic) throws Exception;

    public int update(Categories basic) throws Exception;

    public int delete(String id) throws Exception;
    
}

