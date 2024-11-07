package com.coming.pet_store_coming_be.security;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
  
  private static final String ALGORITHM = "AES";
  private static final String TRANSFORMATION = "AES/GCM/NoPadding";
  private static final int GCM_IV_LENGTH = 12;
  private static final int GCM_TAG_LENGTH = 128;

  private final SecretKey secretKey;

  public AESUtil(String secret) {
    byte[] keyBytes = Base64.getDecoder().decode(secret);

    if(keyBytes.length > 32) {
      byte[] trimmedKey = new byte[32];
      System.arraycopy(keyBytes, 0, trimmedKey, 0, 32);
      keyBytes = trimmedKey;
    }

    this.secretKey = new SecretKeySpec(keyBytes, ALGORITHM);
  }

  public String generateHahs(String data) throws Exception {
    String algorithm = "HmacSHA256";

    SecretKeySpec keySpec = new SecretKeySpec(secretKey.getEncoded(), algorithm);
    Mac mac = Mac.getInstance(algorithm);
    mac.init(keySpec);

    byte[] hmacData = mac.doFinal(data.getBytes());
    return Base64.getEncoder().encodeToString(hmacData);
  }

  // 암호화 메서드
  public String encrypt(String data) throws Exception {
    Cipher cipher = Cipher.getInstance(TRANSFORMATION);
    byte[] iv = new byte[GCM_IV_LENGTH];
    SecureRandom random = new SecureRandom();
    random.nextBytes(iv);
    GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);

    cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmSpec);
    byte[] encryptedData = cipher.doFinal(data.getBytes());
    
    byte[] encryptedDataWithIV = new byte[GCM_IV_LENGTH + encryptedData.length];

    System.arraycopy(iv, 0, encryptedDataWithIV, 0, GCM_IV_LENGTH);
    System.arraycopy(encryptedData, 0, encryptedDataWithIV, GCM_IV_LENGTH, encryptedData.length);

    return Base64.getEncoder().encodeToString(encryptedDataWithIV);
  }

  // 복호화 메서드
  public String decrypth(String encryptedData) throws Exception {
    Cipher cipher = Cipher.getInstance(TRANSFORMATION);
    byte[] decodedData = Base64.getDecoder().decode(encryptedData);

    byte[] iv = new byte[GCM_IV_LENGTH];
    System.arraycopy(decodedData, 0, iv, 0, GCM_IV_LENGTH);
    GCMParameterSpec gcmSpec = new  GCMParameterSpec(GCM_TAG_LENGTH, iv);

    cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmSpec);
    byte[] originalData = cipher.doFinal(decodedData, GCM_IV_LENGTH, decodedData.length - GCM_IV_LENGTH);

    return new String(originalData);
  }

}
