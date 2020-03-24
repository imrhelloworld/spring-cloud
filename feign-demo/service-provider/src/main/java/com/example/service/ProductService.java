package com.example.service;

import com.example.pojo.Product;

import java.util.List;
import java.util.Map;

/**
 * 商品服务
 */
public interface ProductService {

    /**
     * 查询商品列表
     *
     * @return
     */
    List<Product> selectProductList();

    /**
     * 根据主键查询商品
     *
     * @param id
     * @return
     */
    Product selectProductById(Integer id);

    // -------------POST--------------
    /**
     * 根据主键查询商品
     *
     * @param id
     * @return
     */
    Product queryProductById(Integer id);

    /**
     * 新增商品
     *
     * @param product
     * @return
     */
    Map<Object, Object> createProduct(Product product);

    /**
     * 接收商品对象参数
     *
     * @param product
     * @return
     */
    Product selectProductByPojo(Product product);

}