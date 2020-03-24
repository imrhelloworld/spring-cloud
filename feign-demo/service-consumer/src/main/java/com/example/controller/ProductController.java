package com.example.controller;

import com.example.pojo.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 根据主键查询商品
     *
     * @param id
     * @return
     */
    @PostMapping("/info")
    public Product queryProductById(Integer id) {
        return productService.queryProductById(id);
    }

    /**
     * 新增商品
     *
     * @param product
     * @return
     */
    @PostMapping("/save")
    public Map<Object, Object> createProduct(Product product) {
        return productService.createProduct(product);
    }


    /**
     * 接收商品对象参数
     *
     * @param product
     * @return
     */
    @GetMapping("/pojo")
    public Product selectUserByPojo(Product product) {
        return productService.selectProductByPojo(product);
    }

}