package com.bong.o2o.configration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Configuration
public class O2OWebConfiguration implements WebMvcConfigurer {
    private final String uploadPath;

    public O2OWebConfiguration(@Value("${custom.path.upload-images}") String uploadPath) {
        this.uploadPath = uploadPath;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("file:///"+uploadPath+"/")
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));
    }
}
