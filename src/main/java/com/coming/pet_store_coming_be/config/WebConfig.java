package com.coming.pet_store_coming_be.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Value("${cors.allowed-origin}")
  private String allowedOrigin;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**") // 모든 경로에 대해 CORS 허용
      .allowedOrigins(allowedOrigin) // 허용할 출처
      .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드
      .allowedHeaders("*") // 모든 헤더 허용 
      .allowCredentials(true); // 인증 정보를 허용할지 여부
  }

}
