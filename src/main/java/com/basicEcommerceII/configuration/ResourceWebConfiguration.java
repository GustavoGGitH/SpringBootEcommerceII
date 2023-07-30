package com.basicEcommerceII.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceWebConfiguration implements WebMvcConfigurer {
	

	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/vendor/**")
	                .addResourceLocations("classpath:/static/vendor/");
	        registry.addResourceHandler("/css/**")
            .addResourceLocations("classpath:/static/css/");
	        
	        registry.addResourceHandler("/products/**")
            .addResourceLocations("classpath:/static/templates/products/");

	        registry.addResourceHandler("/images/**")
            .addResourceLocations("file:/opt/images/");
	        
	        registry.addResourceHandler("/user/**")
            .addResourceLocations("classpath:/static/templates/user/");
	        
	        registry.addResourceHandler("/administrator/**")
            .addResourceLocations("classpath:/static/templates/administrator/");
	        
	        
	    }
	        	 
	}
