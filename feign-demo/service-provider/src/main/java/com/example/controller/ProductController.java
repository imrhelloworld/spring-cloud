package com.example.controller;

import com.example.pojo.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 查询商品列表
     *
     * @return
     */
    @GetMapping("/list")
    public List<Product> selectProductList() {
        return productService.selectProductList();
    }

    /**
     * 根据主键查询商品
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Product selectProductById(@PathVariable("id") Integer id) {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return productService.selectProductById(id);
    }

    /**
     * 根据主键查询商品
     *
     * @param id
     * @return
     */
    @PostMapping("/single")
    public Product queryProductById(@RequestBody Integer id) {
        return productService.queryProductById(id);
    }

    /**
     * 新增商品
     *
     * @param product
     * @return
     */
    @PostMapping("/save")
    public Map<Object, Object> createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    /**
     * 接收商品对象参数
     *
     * @param product
     * @return
     */
    @GetMapping("/pojo")
    public Product selectUserByPojo(@RequestBody Product product) {
        return productService.selectProductByPojo(product);
    }

}