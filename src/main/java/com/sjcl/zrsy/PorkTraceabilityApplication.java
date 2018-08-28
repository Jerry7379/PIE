package com.sjcl.zrsy;

import com.sjcl.zrsy.controller.LoginFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PorkTraceabilityApplication {

    public static void main(String[] args) {
        SpringApplication.run(PorkTraceabilityApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean LoginFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LoginFilter());
        registration.addUrlPatterns("/Farm/*");
        registration.addUrlPatterns("/Logistics/*");
        registration.addUrlPatterns("/Market/*");
        registration.addUrlPatterns("/Slaughter/*");
        registration.setName("testFilter");
        registration.setOrder(1);
        return registration;
    }

}
