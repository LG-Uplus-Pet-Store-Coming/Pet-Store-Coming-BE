package com.coming.pet_store_coming_be.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coming.pet_store_coming_be.dao.UserDAO;
import com.coming.pet_store_coming_be.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDAO userDAO;

  @Override // Email을 통해 UserDTO 데이터 전달
  public UserDTO emailCheck(String email) throws SQLException {
    return userDAO.getUserByEmail(email).orElse(null);
  }

  @Override // 리프레시 토큰, 토큰 만료 시간, 로그인 여부 상태 업데이트
  public void refreshTokenAndExpiry(String id, String refreshToken, Date tokenExpiry, boolean isActive) throws SQLException {
    Map<String, Object> params = new HashMap<>();

    params.put("id", id);
    params.put("refreshToken", refreshToken);
    params.put("tokenExpiry", tokenExpiry);
    params.put("isActive", isActive);

    userDAO.updateRefreshTokenAndExpiry(params);
  }

  @Override // 로그아웃 (리프레시 토큰, 토큰 만료 시간, 로그인 여부 상태 업데이트)
  public void logout(String userIdentifierId) throws SQLException {
    Map<String, Object> params = new HashMap<>();

    // DB 로그인 여부 상태 업데이트
    params.put("id", userIdentifierId);
    params.put("refreshToken", null);
    params.put("tokenExpiry", null);
    params.put("isActive", false);

    userDAO.updateRefreshTokenAndExpiry(params);
  }

}