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
            registration.addUrlPatterns("/Farm/*");
            registration.addUrlPatterns("/Gov/*");
            registration.addUrlPatterns("/Logistics/*");
            registration.addUrlPatterns("/Market/*");
            registration.addUrlPatterns("/Slaughter/*");
            registration.setName("testFilter");
            registration.setOrder(1);
            return registration;
        }
}
