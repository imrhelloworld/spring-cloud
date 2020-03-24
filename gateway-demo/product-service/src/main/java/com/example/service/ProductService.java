package com.example.service;

import com.example.pojo.Product;

import java.util.List;

/**
 * 商品服务
 */
public interface ProductService {

    /**
     * 根据主键查询商品
     *
     * @param id
     * @return
     */
    Product selectProductById(Integer id);

}