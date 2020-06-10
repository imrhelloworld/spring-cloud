package com.example.controller;

import com.example.pojo.Order;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 根据主键和订单编号查询订单
     *
     * @param id
     * @param orderNo
     * @return
     */
    @GetMapping("/idAndOrderNo")
    public Order selectOrderByIdAndOrderNo(Integer id, String orderNo) {
        return orderService.selectOrderByIdAndOrderNo(id, orderNo);
    }

}
