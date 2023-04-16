package com.example.fuleana.controller;

import com.example.fuleana.entity.User;
import com.example.fuleana.request.UserRequest;
import com.example.fuleana.response.AuthenticatedUserResponse;
import com.example.fuleana.service.RoleService;
import com.example.fuleana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @PostMapping("/user/create")
    public ResponseEntity<?> createUser(@RequestBody UserRequest req){
        userService.createUser(req.getName() , req.getEmail() , req.getPassword(), roleService.getRoleByPk(1L));
        return ResponseEntity.ok("");
    }

    @GetMapping("/me")
    public ResponseEntity<?> getAuthenticatedUser(@NotNull Authentication auth){
        User authUser = userService.getAuthenticatedUser(auth);
        AuthenticatedUserResponse response =
                new AuthenticatedUserResponse(authUser.getName() ,authUser.getEmail(),authUser.getRole().getName(), authUser.isBlocked()  );
        return ResponseEntity.ok(response);
    }

}
