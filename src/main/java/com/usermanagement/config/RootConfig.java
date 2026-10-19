package com.usermanagement.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(
        basePackages = "com.usermanagement",
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = RestController.class
                ),
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = ControllerAdvice.class
                ),
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = WebMvcConfigurer.class
                )
        }
)
@Import({
        PersistenceConfig.class,
        SecurityConfig.class
})
public class RootConfig {
}