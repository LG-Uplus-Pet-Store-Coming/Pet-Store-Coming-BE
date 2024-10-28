package com.coming.pet_store_coming_be.service.auth;

import java.sql.SQLException;

import com.coming.pet_store_coming_be.dto.UserDTO;

public interface AuthService {

  public boolean isUserEmailMath(String email) throws SQLException; // 이메일 중복 확인 비즈니스 로직 인터페이스 메서드
  public UserDTO getUserEmailMath(String email) throws SQLException; // 이메일 값을 통해 사용자 정보를 가져오는 비즈니스 로직 인터페이스 메서드
  public boolean isPasswordMath(String rawPassword, String encryptedPassword); // 입력한 비밀번호와 암호화 된 비밀번호가 같을 지 체크를 위한 비즈니스 로직 인터페이스 메서드
  public boolean signUpUser(UserDTO user) throws SQLException; // 회원가입 비즈니스 로직 인터페이스 메서드
  public void refreshTokenAndExpiry(String id, String refreshToken, boolean isActive) throws SQLException; // 리프레시 토큰, 로그인 여부 상태 업데이트 비즈니스 로직 인터페이스 메서드
  public void logoutUser(String token, String userId) throws SQLException; // 로그아웃 비즈니스 로직 인터페이스 메서드

}
