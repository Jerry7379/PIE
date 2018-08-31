package com.sjcl.zrsy.config;

import com.sjcl.zrsy.controller.LoginFilter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class FilterConfig{
        @Bean
        public FilterRegistrationBean testFilterRegistration() {

            FilterRegistrationBean registration = new FilterRegistrationBean();
            registration.setFilter(new LoginFilter());
            registration.addUrlPatterns("/PorkTraceability/Farm/*");
            registration.addUrlPatterns("/PorkTraceability/Gov/*");
            registration.addUrlPatterns("/PorkTraceability/Logistics/*");
            registration.addUrlPatterns("/PorkTraceability/Market/*");
            registration.addUrlPatterns("/PorkTraceability/Slaughter/*");
            registration.setName("Filter");
            registration.setOrder(1);
            return registration;
        }
}
