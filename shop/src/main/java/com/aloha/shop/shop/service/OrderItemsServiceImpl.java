package com.aloha.shop.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.shop.shop.mapper.OrderItemsMapper;
import com.aloha.shop.shop.model.OrderItems;
import com.aloha.shop.shop.model.Products;

@Service
public class OrderItemsServiceImpl implements OrderItemsService {

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    private ProductsService productsService;

    @Override
    public List<OrderItems> list() throws Exception {
        return orderItemsMapper.list();
    }

    @Override
    public OrderItems select(String id) throws Exception {
        return orderItemsMapper.select(id);
    }

    @Override
    public int insert(OrderItems orderItems) throws Exception {
        int result = orderItemsMapper.insert(orderItems);
        return result;
    }

    @Override
    public int update(OrderItems orderItems) throws Exception {
        int result = orderItemsMapper.update(orderItems);
        return result;
    }

    @Override
    public int delete(String id) throws Exception {
        int result = orderItemsMapper.delete(id);
        return result;
    }

    @Override
    public List<OrderItems> listByOrderId(String ordersId) throws Exception {
        List<OrderItems> orderItems = orderItemsMapper.listByOrderId(ordersId);

        // 주문 항목 - 상품 정보 추가
        for (OrderItems orderItem : orderItems) {
            String productsId = orderItem.getProductsId();
            Products product = productsService.select(productsId);
            orderItem.setProduct(product);
        }

        return orderItems;
    }
    
}
