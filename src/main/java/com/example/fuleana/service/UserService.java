package com.example.fuleana.service;

import com.example.fuleana.entity.Role;
import com.example.fuleana.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User createUser(String name , String email , String pswd , Role role);
    User getUserByPk(Long userId);
    User getAuthenticatedUser(Authentication auth);
    void updateNameByUser(User user, String newName);
    void updateEmailByUser(User user, String newEmail);
    void updatePswdByUser(User user, String newPswd);
    void deleteUserByUser(User user);

}
