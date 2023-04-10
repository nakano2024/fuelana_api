package com.example.fuleana.service;

import com.example.fuleana.entity.User;
import com.example.fuleana.request.UserEmailRequest;
import com.example.fuleana.request.UserNameRequest;
import com.example.fuleana.request.UserPswdRequest;
import com.example.fuleana.request.UserRequest;

public interface UserService {

    User createUser(UserRequest request);
    User getByUserId(Long userId);
    void updateNameByUserId(Long userId, UserNameRequest request);
    void updateEmailByUserId(Long userId, UserEmailRequest request);
    void updatePasswordByUserId(Long userId, UserPswdRequest request);
    void deleteUserByUserId(Long userId);

}
