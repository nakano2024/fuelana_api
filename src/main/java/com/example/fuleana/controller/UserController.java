package com.example.fuleana.controller;

import com.example.fuleana.entity.User;
import com.example.fuleana.request.UserRequest;
import com.example.fuleana.service.RoleService;
import com.example.fuleana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @PostMapping("/user/create")
    public ResponseEntity<?> createUser(@RequestBody UserRequest req){
        User user = userService
                .createUser(req.getName() , req.getEmail() , req.getPassword(), roleService.getRoleByPk(1L));
        return ResponseEntity.ok(user);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getAuthenticatedUser(Authentication auth){
        User user = userService.getAuthenticatedUser(auth);
        return ResponseEntity.ok(user);
    }

}
