package pl.dchruscinski.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoggerInterceptor implements HandlerInterceptor {
    public static final Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);
    public static final String START_TIME = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        request.setAttribute(START_TIME, System.currentTimeMillis());

        logger.info("preHandle(): method: {}, URI: {}, ", request.getMethod(), request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) {

        logger.info("postHandle(): method: {}, URI: {}, processing time: {} ms",
                request.getMethod(), request.getRequestURI(),
                System.currentTimeMillis() - (long) request.getAttribute(START_TIME));
    }
}