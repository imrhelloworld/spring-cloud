package com.example.service;

import com.example.pojo.Order;

public interface OrderService {

    /**
     * 根据主键查询订单
     *
     * @param id
     * @return
     */
    Order selectOrderById(Integer id);

    /**
     * 根据主键查询订单
     *
     * @param id
     * @return
     */
    Order queryOrderById(Integer id);

    /**
     * 根据主键查询订单
     *
     * @param id
     * @return
     */
    Order searchOrderById(Integer id);

}