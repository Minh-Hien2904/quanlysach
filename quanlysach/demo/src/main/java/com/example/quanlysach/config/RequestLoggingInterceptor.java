package com.example.quanlysach.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger(RequestLoggingInterceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("[REQUEST] " + LocalDateTime.now() + " - " + request.getMethod() + " " + request.getRequestURI() + " from " + request.getRemoteAddr());
        return true; // Cho phép request tiếp tục
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("[RESPONSE] " + LocalDateTime.now() + " - " + request.getMethod() + " " + request.getRequestURI() + " - Status: " + response.getStatus());
    }
}
