package com.example.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

public class WebAppInitializer extends AbstractDispatcherServletInitializer {

    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext webApplicationContext = new
                AnnotationConfigWebApplicationContext();
        webApplicationContext.register(WebAppConfig.class);
        return webApplicationContext;
    }

    protected String[] getServletMappings() {
        return new String[] {"/mvc/*"};
    }

    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }
}
