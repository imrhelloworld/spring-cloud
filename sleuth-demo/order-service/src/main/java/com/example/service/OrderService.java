package com.example.service;

import com.example.pojo.Order;

/**
 * 订单服务
 */
public interface OrderService {

    /**
     * 根据主键查询订单
     *
     * @param id
     * @return
     */
    Order selectOrderById(Integer id);

}
