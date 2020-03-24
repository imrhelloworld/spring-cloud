package com.example.service;

import com.example.fallback.ProductServiceFallbackFactory;
import com.example.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// 声明需要调用的服务
@FeignClient(value = "product-service", fallbackFactory = ProductServiceFallbackFactory.class)
public interface ProductService {

    /**
     * 根据主键查询商品
     *
     * @param id
     * @return
     */
    @GetMapping("/product/{id}")
    Product selectProductById(@PathVariable("id") Integer id);

}