package com.coming.pet_store_coming_be.service.auth;

import java.sql.SQLException;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.coming.pet_store_coming_be.dao.auth.AuthDAO;
import com.coming.pet_store_coming_be.dto.UserDTO;
import com.coming.pet_store_coming_be.security.TokenProvider;

@Service
public class AuthServiceImpl implements AuthService {

  @Autowired
  private AuthDAO authDAO;

  @Autowired
  TokenProvider tokenProvider;

  @Override // 회원가입 비즈니스 로직 설계
  public boolean signUpUser(UserDTO user) throws SQLException {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // BCryptPasswordEncoder 인스턴스 생성

    // 비밀번호 암호화 한 값으로 Setter
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    // 사용자의 정보 등록을 성공한 경우
    if(authDAO.insertSignUpUser(user) > 0) {
      return true;
    }

    // 사용자의 정보 등록을 실패한 경우
    return false;
  }
  
  @Override // 이메일 중복 확인 비즈니스 로직 설계
  public boolean isUserEmailMath(String email) throws SQLException {
    return authDAO.getUserByEmail(email).isPresent();
  }

  @Override // 이메일 값을 통해 사용자 정보를 가져오는 비즈니스 로직 설계
  public UserDTO getUserEmailMath(String email) throws SQLException {
    return authDAO.getUserByEmail(email).orElse(null);
  };

  @Override  // 입력한 비밀번호와 암호화 된 비밀번호가 같을 지 체크를 위한 비즈니스 로직 설계
  public boolean isPasswordMath(String rawPassword, String encryptedPassword) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder.matches(rawPassword, encryptedPassword);
  }

  @Override // 소셜 정보 고유키를 통해 회원가입 한 사용자 정보가 있는지 확인
  public UserDTO getSocialUserInfoService(String id) throws SQLException {
    return authDAO.getSocialUserInfo(id);
  }

  // @Override // 소셜 로그인 사용자 정보 조회 비즈니스 로직 인스턴스 메서드
  // public UserDTO getSocialUserInfoService(Long id) throws SQLException {
  //   return authDAO.getSocialUserInfo(id);
  // }

  @Override // 기존 디바이스 토큰 무효화 및 새로운 디바이스 리프레시 토큰 저장
  public void invalidateAndSaveNewRefreshToken(String id, String refreshToken, String deviceId) throws SQLException{
    tokenProvider.invalidatePreviousTokens(id, deviceId); // 기존 디바이스 토큰 무효화
    tokenProvider.saveRefreshToken(id, refreshToken, deviceId); // 새로운 디바이스 리프레시 토큰 저장
  }

  @Override // 로그아웃 비즈니스 로직 설계
  public void logoutUser(String token, String userId) throws SQLException {
    tokenProvider.invalidateToken(token); // 토큰을 블랙리스트에 추가하여 무효화
  }

}
