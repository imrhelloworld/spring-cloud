package com.example.service;

import com.example.pojo.Product;

/**
 * 商品管理
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