package com.example.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.pojo.Order;
import com.example.pojo.Product;
import com.example.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient; // Ribbon 负载均衡器

    /**
     * 根据主键查询订单
     *
     * @param id
     * @return
     */
    @Override
    public Order selectOrderById(Integer id) {
        log.info("订单服务查询订单信息...");
        return new Order(id, "order-001", "中国", 22788D,
                selectProductListByLoadBalancerAnnotation());
    }

    private List<Product> selectProductListByDiscoveryClient() {
        StringBuffer sb = null;

        // 获取服务列表
        List<String> serviceIds = discoveryClient.getServices();
        if (CollectionUtils.isEmpty(serviceIds))
            return null;

        // 根据服务名称获取服务
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("product-service");
        if (CollectionUtils.isEmpty(serviceInstances))
            return null;

        // 构建远程服务调用地址
        ServiceInstance si = serviceInstances.get(0);
        sb = new StringBuffer();
        sb.append("http://" + si.getHost() + ":" + si.getPort() + "/product/list");
        log.info("订单服务调用商品服务...");
        log.info("从注册中心获取到的商品服务地址为：{}", sb.toString());

        // 远程调用服务
        // ResponseEntity: 封装了返回数据
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                sb.toString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                });
        log.info("商品信息查询结果为：{}", JSON.toJSONString(response.getBody()));
        return response.getBody();
    }

    private List<Product> selectProductListByLoadBalancerClient() {
        StringBuffer sb = null;

        // 根据服务名称获取服务
        ServiceInstance si = loadBalancerClient.choose("product-service");
        if (null == si)
            return null;

        sb = new StringBuffer();
        sb.append("http://" + si.getHost() + ":" + si.getPort() + "/product/list");
        log.info("订单服务调用商品服务...");
        log.info("从注册中心获取到的商品服务地址为：{}", sb.toString());

        // ResponseEntity: 封装了返回数据
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                sb.toString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                });
        log.info("商品信息查询结果为：{}", JSON.toJSONString(response.getBody()));
        return response.getBody();
    }

    private List<Product> selectProductListByLoadBalancerAnnotation() {
        String url = "http://product-service/product/list";
        log.info("订单服务调用商品服务...");
        log.info("从注册中心获取到的商品服务地址为：{}", url);
        // ResponseEntity: 封装了返回数据
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                });
        log.info("商品信息查询结果为：{}", JSON.toJSONString(response.getBody()));
        return response.getBody();
    }

}