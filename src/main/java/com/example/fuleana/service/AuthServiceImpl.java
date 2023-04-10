package com.example.fuleana.service;

import com.example.fuleana.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class AuthServiceImpl implements AuthService{

    @Override
    public User getUser(@NotNull Authentication auth) {
        return null;
    }
}
