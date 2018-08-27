package com.sjcl.zrsy;

import com.sjcl.zrsy.controller.PIEFilter;
import com.sjcl.zrsy.controller.TestFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FarmApplication {

    public static void main(String[] args) {
        SpringApplication.run(FarmApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new PIEFilter());
        registration.addUrlPatterns("/Farm/*");
        registration.addUrlPatterns("/Logistics/*");
        registration.addUrlPatterns("/Market/*");
        registration.addUrlPatterns("/Slaughter/*");
        registration.setName("testFilter");
        registration.setOrder(1);
        return registration;



    }
}
