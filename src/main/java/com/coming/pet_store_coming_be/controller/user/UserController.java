package com.coming.pet_store_coming_be.controller.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserService userService;

    @GetMapping("/find-id") // 이메일 찾기 API
    public ResponseEntity<Map<String, Object>> getFindUserEmailController(@RequestParam("name") String email, @RequestParam("phone_number") String phoneNumber) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 이메일 찾기 및 성공 경우
            String findEmail = userService.getFindUserEmailService(email, phoneNumber);

            // 입력으로 주어진 이메일이 없는 경우
            if(findEmail.isBlank()) {
                response.put("status", HttpStatus.NOT_FOUND.value());
                response.put("success", false);
                
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            response.put("status", HttpStatus.OK.value());
            response.put("success", true);
            response.put("email", findEmail);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            // 이메일 찾기에 실패한 경우
            e.printStackTrace();

            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("success", false);
            response.put("message", "Failed to register Canidae information.");
            response.put("errorCode", "CANIDAE_REGISTRATION_ERROR");
            
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @GetMapping("/find-password") // 비밀번호 찾기 API
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    @PutMapping("/find-password/{id}")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {
        //TODO: process PUT request
        
        return entity;
    }
    

    // 회원정보 수정
    @PostMapping("/")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    

}