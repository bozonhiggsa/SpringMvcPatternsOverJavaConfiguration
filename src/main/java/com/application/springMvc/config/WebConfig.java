package com.application.springMvc.config;

import com.application.springMvc.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.util.UrlPathHelper;

/**
 * Java Config - instead of a xml configuration
 * @author Ihor Savchenko
 * @version 1.0
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.application.springMvc.controller")
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");

        return resolver;
    }

    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/handle*")
                .excludePathPatterns("/handle2");
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setStatusCode(HttpStatus.ACCEPTED).setViewName("home");
    }

    // For using @MatrixVariable
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }

}
