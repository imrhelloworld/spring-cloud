package com.example.service.impl;

import com.example.product.pojo.Product;
import com.example.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 商品服务
 * 		timeout 调用该服务的超时时间
 * 		version 为版本号
 * 		group 为分组
 * interface、group、version 三者确定一个服务
 */
@Slf4j
@Service(timeout = 5000, version = "1.0", group = "product-service")
public class ProductServiceImpl implements ProductService {

    /**
     * 查询商品列表
     *
     * @return
     */
    @Override
    public List<Product> selectProductList() {
        log.info("商品服务查询商品信息...");
        return Arrays.asList(
                new Product(1, "华为手机", 1, 5800D),
                new Product(2, "联想笔记本", 1, 6888D),
                new Product(3, "小米平板", 5, 2020D)
        );
    }

}
