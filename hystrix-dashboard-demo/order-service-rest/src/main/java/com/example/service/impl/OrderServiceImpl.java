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
                // 为了方便测试直接使用订单 ID 作为参数
                Arrays.asList(productService.selectProductById(id)));
    }

    // --------------------请求合并---------start-----------
    /*
    public Order searchOrderById(Integer id) {
        // 模拟同一时间用户发起多个请求。
        Future<Product> p1 = productService.selectProductById(1);
        Future<Product> p2 = productService.selectProductById(2);
        Future<Product> p3 = productService.selectProductById(3);
        Future<Product> p4 = productService.selectProductById(4);
        Future<Product> p5 = productService.selectProductById(5);
        try {
            System.out.println(p1.get());
            System.out.println(p2.get());
            System.out.println(p3.get());
            System.out.println(p4.get());
            System.out.println(p5.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new Order(id, "order-003", "中国", 29000D, null);
    }
    */
    // --------------------请求合并----------end------------

}