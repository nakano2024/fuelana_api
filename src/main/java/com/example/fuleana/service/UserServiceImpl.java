package com.example.fuleana.service;

import com.example.fuleana.entity.User;
import com.example.fuleana.repository.UserRepository;
import com.example.fuleana.request.UserEmailRequest;
import com.example.fuleana.request.UserNameRequest;
import com.example.fuleana.request.UserPswdRequest;
import com.example.fuleana.request.UserRequest;
import com.example.fuleana.utility.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    IdGenerator idGenerator;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User createUser(@NotNull UserRequest request) {
        User user = new User();
        String altId = "";
        //ランダム文字列で生成されたaltIdによって、ユーザーが取得できる場合、つまりnullでない場合は一意のaltIdではないため、やり直す
        do{
            altId = idGenerator.generate().trim();
        }while(userRepository.findByAltId(altId).orElse(null) != null);
        user.setAltId(altId);
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setBlocked(false);
        user.setDeleted(false);
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return userRepository.save(user);
    }

    @Override
    public User getByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "User Not Found with ID :" + userId ));
        return user;
    }

    @Override
    public void updateNameByUserId(Long userId, @NotNull UserNameRequest request) {

    }

    @Override
    public void updateEmailByUserId(Long userId, @NotNull UserEmailRequest request) {

    }

    @Override
    public void updatePasswordByUserId(Long userId, @NotNull UserPswdRequest request) {

    }

    @Override
    public void deleteUserByUserId(Long userId) {

    }

}
