package com.coming.pet_store_coming_be.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AESConfig {

  @Value("${aes.secret-key}")
  private String AESSecretKey;

  public String getAESSecretKey() {
    return this.AESSecretKey;
  }
  
}
