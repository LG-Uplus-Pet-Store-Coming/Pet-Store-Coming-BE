package com.coming.pet_store_coming_be.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.coming.pet_store_coming_be.security.AESUtil;

@Configuration
public class AESConfig {

  @Value("${aes.secret-key}")
  private String AESSecretKey;

  public String getAESSecretKey() {
    return this.AESSecretKey;
  }

  // AESUtil을 Bean으로 등록하여 의존성 주입 가능하게 설정
  @Bean
  public AESUtil aesUtil() {
      return new AESUtil(AESSecretKey);
  }
  
}
