package com.example.service.impl;

import com.example.product.pojo.Order;
import com.example.product.service.ProductService;
import com.example.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    // dubbo 提供了 @Reference 注解，可替换 @Autowired 注解，用于引入远程服务
    // 如果注册服务时设置了版本及分组信息，调用远程服务时也要设置对应的版本及分组信息
    @Reference(timeout = 5000, version = "1.0", group = "product-service")
    private ProductService productService;

    /**
     * 根据主键查询订单
     *
     * @param id
     * @return
     */
    @Override
    public Order selectOrderById(Integer id) {
        log.info("订单服务查询订单信息...");
        return new Order(id, "order-001", "中国", 22788D,
                productService.selectProductList());
    }

}