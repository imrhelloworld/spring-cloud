package com.example.service.impl;

import com.example.pojo.Product;
import com.example.service.ProductService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 查询商品列表
     *
     * @return
     */
    // --------------------信号量隔离---------start-----------
    // 声明需要服务容错的方法
    // 信号量隔离
    @HystrixCommand(commandProperties = {
            // 超时时间，默认 1000ms
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                    value = "5000"),
            // 信号量隔离
            @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY,
                    value = "SEMAPHORE"),
            // 信号量最大并发，调小一些方便模拟高并发
            @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_SEMAPHORE_MAX_CONCURRENT_REQUESTS,
                    value = "6")
    }, fallbackMethod = "selectProductListFallback")
    @Override
    public List<Product> selectProductList() {
        // ResponseEntity: 封装了返回数据
        return restTemplate.exchange(
                "http://product-service/product/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                }).getBody();
    }
    // --------------------信号量隔离----------end------------

    // --------------------线程池隔离---------start-----------
    /*
    // 声明需要服务容错的方法
    // 线程池隔离
    @HystrixCommand(groupKey = "order-productService-listPool",// 服务名称，相同名称使用同一个线程池
            commandKey = "selectProductList",// 接口名称，默认为方法名
            threadPoolKey = "order-productService-listPool",// 线程池名称，相同名称使用同一个线程池
            commandProperties = {
                    // 超时时间，默认 1000ms
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = "5000")
            },
            threadPoolProperties = {
                    // 线程池大小
                    @HystrixProperty(name = "coreSize", value = "6"),
                    // 队列等待阈值(最大队列长度，默认 -1)
                    @HystrixProperty(name = "maxQueueSize", value = "100"),
                    // 线程存活时间，默认 1min
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    // 超出队列等待阈值执行拒绝策略
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "100")
            }, fallbackMethod = "selectProductListFallback")
    @Override
    public List<Product> selectProductList() {
        System.out.println(Thread.currentThread().getName() + "-----selectProductList-----");
        // ResponseEntity: 封装了返回数据
        return restTemplate.exchange(
                "http://product-service/product/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                }).getBody();
    }
    */
    // --------------------线程池隔离----------end------------

    // 托底数据
    private List<Product> selectProductListFallback() {
        System.out.println("-----selectProductListFallback-----");
        return Arrays.asList(
                new Product(1, "托底数据-华为手机", 1, 5800D),
                new Product(2, "托底数据-联想笔记本", 1, 6888D),
                new Product(3, "托底数据-小米平板", 5, 2020D)
        );
    }

    // --------------------请求缓存---------start-----------
    /*
    @Cacheable(cacheNames = "orderService:product:list")
    @Override
    public List<Product> selectProductList() {
        // ResponseEntity: 封装了返回数据
        return restTemplate.exchange(
                "http://product-service/product/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                }).getBody();
    }
    */
    // --------------------请求缓存----------end------------

    /**
     * 根据多个主键查询商品
     *
     * @param ids
     * @return
     */
    // 声明需要服务容错的方法
    @HystrixCommand
    @Override
    public List<Product> selectProductListByIds(List<Integer> ids) {
        System.out.println("-----orderService-----selectProductListByIds-----");
        StringBuffer sb = new StringBuffer();
        ids.forEach(id -> sb.append("id=" + id + "&"));
        return restTemplate.getForObject("http://product-service/product/listByIds?" + sb.toString(), List.class);
    }

    /**
     * 根据主键查询商品
     *
     * @param id
     * @return
     */
    // 声明需要服务容错的方法
    // 服务降级
    @HystrixCommand(fallbackMethod = "selectProductByIdFallback")
    @Override
    public Product selectProductById(Integer id) {
        return restTemplate.getForObject("http://product-service/product/" + id, Product.class);
    }

    // --------------------服务熔断---------start-----------
    /*
    // 声明需要服务容错的方法
    // 服务熔断
    @HystrixCommand(commandProperties = {
            // 10s 内请求数大于 20 个就启动熔断器，当请求符合熔断条件触发 fallbackMethod 默认 20 个
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD,
                    value = "10"),
            // 请求错误率大于 50% 就启动熔断器，然后 for 循环发起重试请求，当请求符合熔断条件触发 fallbackMethod
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE,
                    value = "50"),
            // 熔断多少秒后去重试请求，默认 5s
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS,
                    value = "5000"),
    }, fallbackMethod = "selectProductByIdFallback")
    @Override
    public Product selectProductById(Integer id) {
        System.out.println("-----selectProductById-----"
                + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        // 模拟查询主键为 1 的商品信息会导致异常
        if (1 == id)
            throw new RuntimeException("查询主键为 1 的商品信息导致异常");
        return restTemplate.getForObject("http://product-service/product/" + id, Product.class);
    }
    */
    // --------------------服务熔断----------end-----------

    // 托底数据
    private Product selectProductByIdFallback(Integer id) {
        return new Product(id, "托底数据", 1, 2666D);
    }

    // --------------------线程池隔离---------start-----------
    /*
    // 声明需要服务容错的方法
    // 线程池隔离
    @HystrixCommand(groupKey = "order-productService-singlePool",// 服务名称，相同名称使用同一个线程池
            commandKey = "selectProductById",// 接口名称，默认为方法名
            threadPoolKey = "order-productService-singlePool",// 线程池名称，相同名称使用同一个线程池
            commandProperties = {
                    // 超时时间，默认 1000ms
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = "5000")
            },
            threadPoolProperties = {
                    // 线程池大小
                    @HystrixProperty(name = "coreSize", value = "3"),
                    // 队列等待阈值(最大队列长度，默认 -1)
                    @HystrixProperty(name = "maxQueueSize", value = "100"),
                    // 线程存活时间，默认 1min
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    // 超出队列等待阈值执行拒绝策略
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "100")
            })
    @Override
    public Product selectProductById(Integer id) {
        System.out.println(Thread.currentThread().getName() + "-----selectProductById-----");
        return restTemplate.getForObject("http://product-service/product/" + id, Product.class);
    }
    */
    // --------------------线程池隔离----------end------------

    // --------------------请求合并---------start-----------
    /*
    // 处理请求合并的方法一定要支持异步，返回值必须是 Future<T>
    // 合并请求
    @HystrixCollapser(batchMethod = "selectProductListByIds", // 合并请求方法
            scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL, // 请求方式
            collapserProperties = {
                    // 间隔多久的请求会进行合并，默认 10ms
                    @HystrixProperty(name = "timerDelayInMilliseconds", value = "20"),
                    // 批处理之前，批处理中允许的最大请求数
                    @HystrixProperty(name = "maxRequestsInBatch", value = "200")
            })
    @Override
    public Future<Product> selectProductById(Integer id) {
        System.out.println("-----orderService-----selectProductById-----");
        return null;
    }
    */
    // --------------------请求合并----------end------------

    // --------------------请求缓存---------start-----------
    /*
    @Cacheable(cacheNames = "orderService:product:single", key = "#id")
    @Override
    public Product selectProductById(Integer id) {
        return restTemplate.getForObject("http://product-service/product/" + id, Product.class);
    }
    */
    // --------------------请求缓存----------end------------

}
