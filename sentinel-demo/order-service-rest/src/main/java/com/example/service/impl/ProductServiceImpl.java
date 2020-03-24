package com.example.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.pojo.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 商品管理
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 根据主键查询商品
     *
     * @param id
     * @return
     */
    @SentinelResource(value = "selectProductById",
            blockHandler = "selectProductByIdBlockHandler", fallback = "selectProductByIdFallback")
    @Override
    public Product selectProductById(Integer id) {
        // 模拟查询主键为 1 的商品信息会导致异常
        // if (1 == id)
        //     throw new RuntimeException("查询主键为 1 的商品信息导致异常");
        return restTemplate.getForObject("http://product-service/product/" + id, Product.class);
    }

    // 服务流量控制处理，参数最后多一个 BlockException，其余与原函数一致。
    public Product selectProductByIdBlockHandler(Integer id, BlockException ex) {
        // Do some log here.
        ex.printStackTrace();
        return new Product(id, "服务流量控制处理-托底数据", 1, 2666D);
    }

    // 服务熔断降级处理，函数签名与原函数一致或加一个 Throwable 类型的参数
    public Product selectProductByIdFallback(Integer id, Throwable throwable) {
        System.out.println("product-service 服务的 selectProductById 方法出现异常，异常信息如下："
                + throwable);
        return new Product(id, "服务熔断降级处理-托底数据", 1, 2666D);
    }

}
