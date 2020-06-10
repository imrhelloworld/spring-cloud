package com.example.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
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
     * 根据主键和订单编号查询订单
     *
     * @param id
     * @param orderNo
     * @return
     */
    @Override
    @SentinelResource(value = "selectOrderByIdAndOrderNo",
            blockHandler = "selectOrderByIdAndOrderNoBlockHandler",
            fallback = "selectOrderByIdAndOrderNoFallback")
    public Order selectOrderByIdAndOrderNo(Integer id, String orderNo) {
        return new Order(id, orderNo, "中国", 2666D,
                Arrays.asList(productService.selectProductById(1)));
    }

    // 服务流量控制处理，参数最后多一个 BlockException，其余与原函数一致。
    public Order selectOrderByIdAndOrderNoBlockHandler(Integer id, String orderNo,
                                                       BlockException ex) {
        // Do some log here.
        ex.printStackTrace();
        return new Order(id, "服务流量控制处理-托底数据", "中国", 2666D,
                Arrays.asList(productService.selectProductById(1)));
    }

    // 服务熔断降级处理，函数签名与原函数一致或加一个 Throwable 类型的参数
    public Order selectOrderByIdAndOrderNoFallback(Integer id, String orderNo,
                                                   Throwable throwable) {
        System.out.println("order-service 服务的 selectOrderById 方法出现异常，异常信息如下："
                + throwable);
        return new Order(id, "服务熔断降级处理-托底数据", "中国", 2666D,
                Arrays.asList(productService.selectProductById(1)));
    }

}