package com.example.fallback;

import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.BlockResponse;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.ZuulBlockFallbackProvider;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对订单服务做服务容错处理
 */
public class OrderBlockFallbackProvider implements ZuulBlockFallbackProvider {

    private Logger logger = LoggerFactory.getLogger(OrderBlockFallbackProvider.class);

    @Override
    public String getRoute() {
        return "order-service"; // 服务名称
    }

    @Override
    public BlockResponse fallbackResponse(String route, Throwable cause) {
        logger.error("{} 服务触发限流", route);
        if (cause instanceof BlockException) {
            return new BlockResponse(429, "服务访问压力过大，请稍后再试。", route);
        } else {
            return new BlockResponse(500, "系统错误，请联系管理员。", route);
        }
    }

}