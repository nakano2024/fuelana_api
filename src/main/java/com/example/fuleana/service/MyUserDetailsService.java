package com.example.fuleana.service;

import com.example.fuleana.entity.MyUserDetails;
import com.example.fuleana.entity.User;
import com.example.fuleana.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final String trimmedEmail = email.trim();
        User user = repository.findByEmail(trimmedEmail)
                .orElseThrow(()-> new UsernameNotFoundException("User Not Found with Email: " + trimmedEmail));
        return new MyUserDetails(user);
    }

}
