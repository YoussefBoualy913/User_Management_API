package com.usermanagement.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@EnableMethodSecurity
@ComponentScan(
        basePackages = "com.usermanagement",
        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = RestController.class
                ),
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = ControllerAdvice.class
                )
        }
)
public class WebConfig implements WebMvcConfigurer {
}