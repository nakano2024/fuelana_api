package com.example.fuleana.controller;

import com.example.fuleana.request.UserRequest;
import com.example.fuleana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user/create")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.createUser(userRequest));
    }

    @GetMapping("/me")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("");
    }

}
