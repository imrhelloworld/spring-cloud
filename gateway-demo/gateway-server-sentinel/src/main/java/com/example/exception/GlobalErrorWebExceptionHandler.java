package com.example.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 网关全局异常处理器
 */
// @Component
public class GlobalErrorWebExceptionHandler implements ErrorWebExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalErrorWebExceptionHandler.class);

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        /**
         * 按照异常类型进行处理
         */
        // HttpStatus httpStatus;
        // String body;
        // if (throwable instanceof NotFoundException) {
        //     httpStatus = HttpStatus.NOT_FOUND;
        //     body = HttpStatus.NOT_FOUND.getReasonPhrase();
        // } else if (throwable instanceof ResponseStatusException) {
        //     ResponseStatusException responseStatusException = (ResponseStatusException) throwable;
        //     httpStatus = responseStatusException.getStatus();
        //     body = responseStatusException.getMessage();
        // } else {
        //     httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        //     body = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        // }
        // /**
        //  * 封装响应体,此body可修改为自己的jsonBody
        //  */
        // Map<String, Object> result = new HashMap<>();
        // result.put("httpStatus", httpStatus);
        // result.put("body", body);
        // /**
        //  * 错误记录
        //  */
        // ServerHttpRequest request = serverWebExchange.getRequest();
        // logger.error("[网关全局异常处理器]异常请求路径：{}，记录异常信息：{}",
        //         request.getPath(), throwable.getMessage());

        ServerHttpResponse response = serverWebExchange.getResponse();
        // 响应类型
        response.getHeaders().add("Content-Type", "application/json; charset=utf-8");
        // 响应状态码，HTTP 401 错误代表用户没有访问权限
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        // 响应内容
        String message = "{\"message\":\"" + HttpStatus.UNAUTHORIZED.getReasonPhrase() + "\"}";
        DataBuffer buffer = response.bufferFactory().wrap(message.getBytes());
        // 请求结束，不在继续向下请求
        return response.writeWith(Mono.just(buffer));
    }

}
