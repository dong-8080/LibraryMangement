package com.example.demo.config;

import com.example.demo.exception.DescribeException;
import com.example.demo.exception.ResultCode;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@Configuration
public class MyWebConfiguration extends WebMvcConfigurationSupport {

    public static final String SESSION_KEY = "token";

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(new LoginInterceptor());
        addInterceptor.excludePathPatterns("/error", "/login", "/register","/hello","/index")
                .addPathPatterns("/**");
    }

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    private class LoginInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            HttpSession session = request.getSession();
            if (session.getAttribute(SESSION_KEY) != null) {
                return true;
            } else {
                throw new DescribeException(ResultCode.USER_NOT_LOGIN);
            }
        }
    }


}
