package fr.aleclerc.windpath.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {


    private static final String[] ROUTER = {"/path"};


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**")//
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler(ROUTER)//
                .addResourceLocations("classpath:/static/index.html");

        registry.addResourceHandler("/")//
                .addResourceLocations("classpath:/static/index.html");

    }


}