package com.example.exception;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.example.pojo.Product;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

public class ExceptionUtil {

    // 服务流量控制处理
    public static ClientHttpResponse handleException(HttpRequest request,
                                                     byte[] body,
                                                     ClientHttpRequestExecution execution,
                                                     BlockException exception) {
        exception.printStackTrace();
        return new SentinelClientHttpResponse(
                JSON.toJSONString(new Product(1, "服务流量控制处理-托底数据", 1, 2666D)));
    }

    // 服务熔断降级处理
    public static ClientHttpResponse fallback(HttpRequest request,
                                                    byte[] body,
                                                    ClientHttpRequestExecution execution,
                                                    BlockException exception) {
        exception.printStackTrace();
        return new SentinelClientHttpResponse(
                JSON.toJSONString(new Product(1, "服务熔断降级处理-托底数据", 1, 2666D)));
    }

}