package com.example.fuleana.service;

import com.example.fuleana.entity.MyUserDetails;
import com.example.fuleana.entity.Role;
import com.example.fuleana.entity.User;
import com.example.fuleana.repository.UserRepository;
import com.example.fuleana.request.UserEmailRequest;
import com.example.fuleana.request.UserNameRequest;
import com.example.fuleana.request.UserPswdRequest;
import com.example.fuleana.request.UserRequest;
import com.example.fuleana.utility.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
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
    public User createUser(String name , String email , String pswd, @NotNull Role role) {
        User user = new User();
        String altId = "";
        //ランダム文字列で生成されたaltIdによって、ユーザーが取得できる場合、つまりnullでない場合は一意のaltIdではないため、やり直す
        do{
            altId = idGenerator.generate().trim();
        }while(userRepository.findByAltId(altId).orElse(null) != null);
        user.setAltId(altId);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(pswd));
        user.setRole(role);
        user.setBlocked(false);
        user.setDeleted(false);
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return userRepository.save(user);
    }

    @Override
    public User getUserByPk(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "User Not Found with ID :" + userId ));
        return user;
    }

    @Override
    public User getAuthenticatedUser(@NotNull Authentication auth) {
        @NotNull MyUserDetails userDetails = auth.getPrincipal() instanceof MyUserDetails?
                (MyUserDetails)auth.getPrincipal() : null;
        final String trimmedAltId = userDetails.getAltId().trim();
        User authenticatedUser = userRepository.findByAltId(trimmedAltId)
                .orElseThrow(()->new EntityNotFoundException("User Not Found with ID: " + trimmedAltId));
        return authenticatedUser;
    }

    @Override
    public void updateNameByUser(@NotNull User user, String newName) {
        user.setName(newName);
        userRepository.save(user);
    }

    @Override
    public void updateEmailByUser(@NotNull User user, String newEmail) {
        user.setEmail(newEmail);
        userRepository.save(user);

    }

    @Override
    public void updatePswdByUser(@NotNull User user, String newPswd) {
        user.setPassword(newPswd);
        userRepository.save(user);
    }

    @Override
    public void deleteUserByUser(@NotNull User user) {
        user.setDeleted(true);
        userRepository.save(user);
    }

}
