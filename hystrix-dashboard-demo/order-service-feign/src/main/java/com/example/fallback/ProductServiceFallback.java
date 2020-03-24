package com.example.fallback;

import com.example.pojo.Product;
import com.example.service.ProductService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 服务熔断降级处理
 */
// @Component
public class ProductServiceFallback implements ProductService {

    // 查询商品列表接口的托底数据
    @Override
    public List<Product> selectProductList() {
        return Arrays.asList(
                new Product(1, "托底数据-华为手机", 1, 5800D),
                new Product(2, "托底数据-联想笔记本", 1, 6888D),
                new Product(3, "托底数据-小米平板", 5, 2020D)
        );
    }

    // 根据多个主键查询商品接口的托底数据
    @Override
    public List<Product> selectProductListByIds(List<Integer> ids) {
        List<Product> products = new ArrayList<>();
        ids.forEach(id -> products.add(new Product(id, "托底数据-电视机" + id, 1, 5800D)));
        return products;
    }

    // 根据主键查询商品接口的托底数据
    @Override
    public Product selectProductById(Integer id) {
        return new Product(id, "托底数据", 1, 2666D);
    }

}