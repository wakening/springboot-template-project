package com.example.provider.runtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wakening
 */
@ConditionalOnMissingBean(name = "requestLoggingFilterRegistration")
@Configuration
public class ProjectWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private UrlInterceptor urlInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(urlInterceptor).addPathPatterns("/**");
    }

}
