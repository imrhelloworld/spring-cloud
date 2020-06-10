package com.example.fallback;

import com.example.pojo.Product;
import com.example.service.ProductService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 服务熔断降级处理可以捕获异常
 */
@Slf4j
@Component
public class ProductServiceFallbackFactory implements FallbackFactory<ProductService> {

    @Override
    public ProductService create(Throwable throwable) {
        return new ProductService() {
            @Override
            public Product selectProductById(Integer id) {
                // 获取日志，在需要捕获异常的方法中进行处理
                log.error("product-service 服务的 selectProductById 方法出现异常，异常信息如下："
                        + throwable);
                return new Product(id, "托底数据", 1, 2666D);
            }
        };
    }

}