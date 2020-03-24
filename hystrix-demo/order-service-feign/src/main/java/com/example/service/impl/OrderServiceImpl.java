package com.example.service.impl;

import com.example.pojo.Order;
import com.example.service.OrderService;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    /**
     * 根据主键查询订单
     *
     * @param id
     * @return
     */
    @Override
    public Order selectOrderById(Integer id) {
        return new Order(id, "order-001", "中国", 22788D,
                productService.selectProductList());
    }

    /**
     * 根据主键查询订单
     *
     * @param id
     * @return
     */
    @Override
    public Order queryOrderById(Integer id) {
        return new Order(id, "order-002", "中国", 11600D,
                productService.selectProductListByIds(Arrays.asList(1, 2)));
    }

    /**
     * 根据主键查询订单
     *
     * @param id
     * @return
     */
    @Override
    public Order searchOrderById(Integer id) {
        return new Order(id, "order-003", "中国", 2666D,
                Arrays.asList(productService.selectProductById(5)));
    }

}