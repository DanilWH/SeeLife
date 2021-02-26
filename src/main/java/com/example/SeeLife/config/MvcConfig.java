package com.example.SeeLife.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    
    @Value("${upload.path}")
    private String uploadPath;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file://" + this.uploadPath + "/images/");
        
        registry.addResourceHandler("/video/**")
                .addResourceLocations("file://" + this.uploadPath + "/videos/");
        
        registry.addResourceHandler("/audio/**")
                .addResourceLocations("file://" + this.uploadPath + "/audios/");
        
        registry.addResourceHandler("/document/**")
                .addResourceLocations("file://" + this.uploadPath + "/documents/");
        
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
