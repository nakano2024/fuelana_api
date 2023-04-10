package com.example.fuleana.service;

import com.example.fuleana.entity.User;
import org.springframework.security.core.Authentication;

public interface AuthService {

    User getUser(Authentication auth);

}
