package com.example.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 异常过滤器
 */
@Component
public class ErrorFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(ErrorFilter.class);

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext rc = RequestContext.getCurrentContext();
        ZuulException exception = this.findZuulException(rc.getThrowable());
        logger.error("ErrorFilter..." + exception.errorCause, exception);

        HttpStatus httpStatus = null;
        if (429 == exception.nStatusCode)
            httpStatus = HttpStatus.TOO_MANY_REQUESTS;

        if (500 == exception.nStatusCode)
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        // 响应状态码
        rc.setResponseStatusCode(httpStatus.value());
        // 响应类型
        rc.getResponse().setContentType("application/json; charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = rc.getResponse().getWriter();
            // 响应内容
            writer.print("{\"message\":\"" + httpStatus.getReasonPhrase() + "\"}");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != writer)
                writer.close();
        }
        return null;
    }

    private ZuulException findZuulException(Throwable throwable) {
        if (throwable.getCause() instanceof ZuulRuntimeException)
            return (ZuulException) throwable.getCause().getCause();

        if (throwable.getCause() instanceof ZuulException)
            return (ZuulException) throwable.getCause();

        if (throwable instanceof ZuulException)
            return (ZuulException) throwable;
        return new ZuulException(throwable, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
    }

}