package com.example.provider.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ServletContextRequestLoggingFilter;

import java.util.Collections;

/**
 * 过滤器配置类
 *
 * @author wakening
 */
@Configuration
public class ServletFilterConfig {

    /**
     * 原始请求报文打印。
     * <p>
     * https://www.jianshu.com/p/0bb1ca8644a0
     *
     * @return Filter
     * @see org.springframework.web.util.ContentCachingRequestWrapper
     */
    @ConditionalOnProperty(prefix = "template.filter.requestLoggingFilterRegistration", name = "enabled", matchIfMissing = true)
    @Bean
    public FilterRegistrationBean<ServletContextRequestLoggingFilter> requestLoggingFilterRegistration() {
        ServletContextRequestLoggingFilter filter = new ServletContextRequestLoggingFilter();
        filter.setIncludePayload(true);
        filter.setIncludeClientInfo(true);
        filter.setMaxPayloadLength(50000);
        FilterRegistrationBean<ServletContextRequestLoggingFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(filter);
        // url白名单
        registration.setUrlPatterns(Collections.singleton("/*"));
        return registration;
    }

}
