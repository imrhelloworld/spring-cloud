package com.example.fallback;

import com.example.pojo.Product;
import com.example.service.ProductService;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 服务熔断降级处理可以捕获异常
 */
@Component
public class ProductServiceFallbackFactory implements FallbackFactory<ProductService> {

    // 获取日志，在需要捕获异常的方法中进行处理
    Logger logger = LoggerFactory.getLogger(ProductServiceFallbackFactory.class);

    @Override
    public ProductService create(Throwable throwable) {
        return new ProductService() {
            // 查询商品列表接口的托底数据
            @Override
            public List<Product> selectProductList() {
                logger.error("product-service 服务的 selectProductList 方法出现异常，异常信息如下："
                        + throwable);
                return Arrays.asList(
                        new Product(1, "托底数据-华为手机", 1, 5800D),
                        new Product(2, "托底数据-联想笔记本", 1, 6888D),
                        new Product(3, "托底数据-小米平板", 5, 2020D)
                );
            }

            // 根据多个主键查询商品接口的托底数据
            @Override
            public List<Product> selectProductListByIds(List<Integer> ids) {
                logger.error("product-service 服务的 selectProductListByIds 方法出现异常，异常信息如下："
                        + throwable);
                List<Product> products = new ArrayList<>();
                ids.forEach(id -> products.add(new Product(id, "托底数据-电视机" + id, 1, 5800D)));
                return products;
            }

            // 根据主键查询商品接口的托底数据
            @Override
            public Product selectProductById(Integer id) {
                logger.error("product-service 服务的 selectProductById 方法出现异常，异常信息如下："
                        + throwable);
                return new Product(id, "托底数据", 1, 2666D);
            }
        };
    }

}