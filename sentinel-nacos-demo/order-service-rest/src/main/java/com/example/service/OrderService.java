package com.example.service;

import com.example.pojo.Order;

public interface OrderService {

    /**
     * 根据主键和订单编号查询订单
     *
     * @param id
     * @param orderNo
     * @return
     */
    Order selectOrderByIdAndOrderNo(Integer id, String orderNo);

}