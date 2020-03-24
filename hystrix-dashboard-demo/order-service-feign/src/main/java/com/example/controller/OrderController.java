package com.example.controller;

import com.example.pojo.Order;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 根据主键查询订单-调用商品服务 /product/list
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}/product/list")
    public Order selectOrderById(@PathVariable("id") Integer id) {
        return orderService.selectOrderById(id);
    }

    /**
     * 根据主键查询订单-调用商品服务 /product/listByIds
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}/product/listByIds")
    public Order queryOrderById(@PathVariable("id") Integer id) {
        return orderService.queryOrderById(id);
    }

    /**
     * 根据主键查询订单-调用商品服务 /product/{id}
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}/product")
    public Order searchOrderById(@PathVariable("id") Integer id) {
        return orderService.searchOrderById(id);
    }

}
