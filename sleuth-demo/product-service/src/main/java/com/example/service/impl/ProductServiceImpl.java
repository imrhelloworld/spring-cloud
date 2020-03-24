package com.example.service.impl;

import com.example.pojo.Product;
import com.example.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品服务
 */
@Service
public class ProductServiceImpl implements ProductService {

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
     * 根据多个主键查询商品
     *
     * @param ids
     * @return
     */
    @Override
    public List<Product> selectProductListByIds(List<Integer> ids) {
        List<Product> products = new ArrayList<>();
        ids.forEach(id -> products.add(new Product(id, "电视机" + id, 1, 5888D)));
        return products;
    }

}