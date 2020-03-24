package com.example.service.impl;

import com.example.pojo.Product;
import com.example.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品服务
 */
@Service
public class ProductServiceImpl implements ProductService {

    /**
     * 查询商品列表
     *
     * @return
     */
    @Override
    public List<Product> selectProductList() {
        return Arrays.asList(
                new Product(1, "华为手机-7070", 1, 5800D),
                new Product(2, "联想笔记本", 1, 6888D),
                new Product(3, "小米平板", 5, 2020D)
        );
    }

    /**
     * 根据主键查询商品
     *
     * @param id
     * @return
     */
    @Override
    public Product selectProductById(Integer id) {
        return new Product(id, "冰箱", 1, 2666D);
    }

    /**
     * 根据主键查询商品
     *
     * @param id
     * @return
     */
    @Override
    public Product queryProductById(Integer id) {
        return new Product(id, "冰箱", 1, 2666D);
    }

    /**
     * 新增商品
     *
     * @param product
     * @return
     */
    @Override
    public Map<Object, Object> createProduct(Product product) {
        System.out.println(product);
        return new HashMap<Object, Object>() {{
            put("code", 200);
            put("message", "新增成功");
        }};
    }

    /**
     * 接收商品对象参数
     *
     * @param product
     * @return
     */
    public Product selectProductByPojo(Product product) {
        System.out.println(product);
        return product;
    }

}