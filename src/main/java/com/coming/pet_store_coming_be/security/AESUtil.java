package com.coming.pet_store_coming_be.security;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
  
  private static final String ALGORITHM = "AES";
  private static final String TRANSFORMATION = "AES/GCM/NoPaaing";
  private static final int GCM_IV_LENGTH = 12;
  private static final int GCM_TAG_LENGHT = 128;

  private final SecretKey secretKey;

  public AESUtil(String secret) {
    byte[] keyBytes = secret.getBytes();
    this.secretKey = new SecretKeySpec(keyBytes, ALGORITHM);
  }

  // 암호화 메서드
  public String encrypt(String data) throws Exception {
    Cipher cipher = Cipher.getInstance(TRANSFORMATION);
    byte[] iv = new byte[GCM_IV_LENGTH];
    SecureRandom random = new SecureRandom();
    random.nextBytes(iv);
    GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGHT, iv);

    cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmSpec);
    byte[] encryptedData = cipher.doFinal(data.getBytes());
    
    byte[] encryptedDataWithIV = new byte[GCM_IV_LENGTH + encryptedData.length];

    System.arraycopy(iv, 0, encryptedDataWithIV, 0, GCM_IV_LENGTH);
    System.arraycopy(encryptedData, 0, encryptedDataWithIV, GCM_IV_LENGTH, encryptedData.length);

    return Base64.getEncoder().encodeToString(encryptedDataWithIV);
  }

  // 복호화 메서드

}
