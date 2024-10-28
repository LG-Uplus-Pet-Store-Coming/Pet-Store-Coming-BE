package com.coming.pet_store_coming_be.controller.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/user")
public class UserGetController {@GetMapping("path")

    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
}