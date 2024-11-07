package com.coming.pet_store_coming_be.controller.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coming.pet_store_coming_be.dto.UserDTO;
import com.coming.pet_store_coming_be.service.user.UserService;

import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserService userService;

    @GetMapping("/recover-credentials/email") // 이메일과 비밀번호 복구 절차를 포괄하는 API
    public ResponseEntity<Map<String, Object>> getFindUserEmailController(@RequestParam("name") String name, @RequestParam("phone_number") String phoneNumber) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 이메일 찾기 및 성공 경우
            Map<String, Object> findInfo = userService.getFindUserEmailService(name, phoneNumber);

            // 입력으로 주어진 정보가 없을 경우
            if(findInfo == null) {
                response.put("status", HttpStatus.NOT_FOUND.value());
                response.put("success", false);
                response.put("messagee", "User information not found.");
                response.put("errorCode", "USER_NOT_FOUND");
                
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            // 소셜 로그인을 통해서 회원가입을 사용자일 경우
            if(findInfo.get("platform") != null && !findInfo.get("platform").toString().isEmpty()) {
                response.put("status", HttpStatus.FORBIDDEN.value());
                response.put("success", false);
                response.put("messagee", "User is registered through social login.");
                response.put("errorCode", "SOCIAL_LOGIN_USER");
                response.put("platform", findInfo.get("platform"));
                
                return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
            }

            // 사용자 정보 찾기 성공
            response.put("status", HttpStatus.OK.value());
            response.put("success", true);
            response.put("data", findInfo);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            // 이메일 찾기에 실패한 경우
            e.printStackTrace();

            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("success", false);
            response.put("message", "Email lookup failed.");
            response.put("errorCode", "EMAIL_LOOKUP_ERROR");
            
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @PutMapping("/recover-credentials/reset-password") // 비밀번호 재설정 API
    public ResponseEntity<Map<String, Object>> updateUserPassword(@RequestBody Map<String, String> updateUserInfo) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 비밀번호 재설정
            userService.updateUserPassswordService(updateUserInfo);

            response.put("status", HttpStatus.OK.value());
            response.put("success", true);

            return new ResponseEntity<>(response, HttpStatus.OK);


        } catch (Exception e) {
            // 비밀번호 수정에 실패한 경우
            e.printStackTrace();

            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("success", false);
            response.put("message", "Password reset failed.");
            response.put("errorCode", "PASSWORD_RESET_ERROR");
            
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    // 회원정보 수정
    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> updateUserInfoController(@RequestBody UserDTO user) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 회원정보 수정
            userService.updateUserInfoService(user);

            response.put("status", HttpStatus.OK.value());
            response.put("success", true);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            // 비밀번호 수정에 실패한 경우
            e.printStackTrace();

            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("success", false);
            response.put("message", "User information update failed.");
            response.put("errorCode", "USER_UPDATE_ERROR");
            
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

}