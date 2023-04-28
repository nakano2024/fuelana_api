package com.example.fuleana.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        final String allowedOriginEnv = System.getenv("ALLOWED_ORIGIN");
        final String allowedOrigin = allowedOriginEnv!= null?
                allowedOriginEnv : "http://localhost:3000";

        registry.addMapping("/**")
                .allowedOrigins(allowedOrigin)
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(false) //クッキーの設定
                .maxAge(3600);

    }

}
