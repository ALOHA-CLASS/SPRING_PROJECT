package com.aloha.shop.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.shop.shop.mapper.OrdersMapper;
import com.aloha.shop.shop.model.OrderItems;
import com.aloha.shop.shop.model.Orders;
import com.aloha.shop.shop.model.Products;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private ProductsService productsService;

    @Autowired
    private OrderItemsService orderItemsService;

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public List<Orders> list() throws Exception {
        return ordersMapper.list();
    }

    @Override
    public Orders select(String id) throws Exception {
        return ordersMapper.select(id);
    }

    @Override
    public int insert(Orders orders) throws Exception {
        List<String> productIdList = orders.getProductId();
        List<Integer> quantityList = orders.getQuantity();

        if( productIdList == null ) return 0;
        if( quantityList == null ) return 0;
        if( productIdList.size() != quantityList.size()) return 0;

        String orderId = UUID.randomUUID().toString();
        orders.setId(orderId);

        int totalCount = productIdList.size();
        int totalQuantity = 0;
        int totalPrice = 0;
        String title = "";

        List<OrderItems> orderItemList = new ArrayList<>();
        for (int i = 0; i < productIdList.size(); i++) {
            String productId = productIdList.get(i);
            Products product = productsService.select(productId);
            if( i == 0 ) title = product.getName();
            if( product == null ) continue;
            OrderItems orderItem = new OrderItems();
            orderItem.setId(UUID.randomUUID().toString());
            orderItem.setOrdersId(orderId);
            orderItem.setProductsId(productId);
            int quantity = quantityList.get(i);
            int price = product.getPrice();
            int amount = price*quantity;
            totalPrice += amount;
            totalQuantity += quantity;
            orderItem.setQuantity(quantity);
            orderItem.setPrice(price);
            orderItem.setAmount(amount);
            orderItemList.add(orderItem);
        }
        title += " 외 " + totalCount + "종";
        
        orders.setTitle(title);
        orders.setTotalPrice(totalPrice);
        orders.setTotalQuantity(totalQuantity);
        orders.setTotalCount(totalCount);

        int result = ordersMapper.insert(orders);
        if( result > 0 ) {
            for (OrderItems orderItems : orderItemList) {
                orderItemsService.insert(orderItems);
            }
        }
        return result;
    }

    @Override
    public int update(Orders orders) throws Exception {
        int result = ordersMapper.update(orders);
        return result;
    }

    @Override
    public int delete(String id) throws Exception {
        int result = ordersMapper.delete(id);
        return result;
    }
    
}
