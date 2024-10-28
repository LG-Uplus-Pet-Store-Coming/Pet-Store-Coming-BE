package com.coming.pet_store_coming_be.service.auth;

import java.sql.SQLException;

import java.util.UUID;
import java.util.Map;
import java.util.HashMap;

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
    String uuid = UUID.randomUUID().toString(); // 사용자의 고유 번호 생성

    // 사용자 고유 번호, 비밀번호 암호화 한 값으로 Set
    user.setId(uuid);
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

  @Override // 리프레시 토큰, 로그인 여부 상태 업데이트 비즈니스 로직 설계
  public void refreshTokenAndExpiry(String id, String refreshToken, boolean isActive) throws SQLException{
    Map<String, Object> params = new HashMap<>();

    params.put("id", id);
    params.put("refreshToken", refreshToken);
    params.put("isActive", isActive);

    authDAO.updateRefreshTokenAndExpiry(params);
  }

  @Override // 리프레시 토큰, 로그인 여부 상태 업데이트 비즈니스 로직 설계
  public void logoutUser(String token, String userId) throws SQLException {
    tokenProvider.invalidateToken(token); // 토큰을 블랙리스트에 추가하여 무효화

    // 사용자 정보 업데이트(isActive 상태, refreshToken 삭제)
    Map<String, Object> params = new HashMap<>();
    params.put("id", userId);
    params.put("refreshToken", null);
    params.put("isActive", false);

    authDAO.updateRefreshTokenAndExpiry(params);
  }

}
