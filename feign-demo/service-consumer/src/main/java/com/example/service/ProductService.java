package com.example.service;

import com.example.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

// 声明需要调用的服务
@FeignClient("service-provider")
public interface ProductService {

    /**
     * 查询商品列表
     *
     * @return
     */
    // 配置需要调用的服务地址及参数
    @GetMapping("/product/list")
    List<Product> selectProductList();

    /**
     * 根据主键查询商品
     *
     * @return
     */
    @GetMapping("/product/{id}")
    Product selectProductById(@PathVariable("id") Integer id);

    /**
     * 根据主键查询商品
     *
     * @param id
     * @return
     */
    @PostMapping("/product/single")
    Product queryProductById(Integer id);

    /**
     * 新增商品
     *
     * @param user
     * @return
     */
    @PostMapping("/product/save")
    Map<Object, Object> createProduct(Product user);

    /**
     * 接收商品对象参数
     *
     * @param product
     * @return
     */
    @GetMapping("/product/pojo")
    Product selectProductByPojo(Product product);

}