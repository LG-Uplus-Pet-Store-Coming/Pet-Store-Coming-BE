package com.coming.pet_store_coming_be.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3Config {
  
  @Value("${spring.cloud.aws.region.static}") private String region;
  @Value("${spring.cloud.aws.credentials.access-key}") private String accessKey;
  @Value("${spring.cloud.aws.credentials.secret-key}") private String secretKey;

  @Bean
  public AmazonS3 amazonS3() {
    return AmazonS3ClientBuilder
      .standard()
      .withRegion(region)
      .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
      .build();
  }

}
