package com.example.fuleana.service;

import com.example.fuleana.entity.MyUserDetails;
import com.example.fuleana.entity.User;
import com.example.fuleana.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsServiceImpl implements MyUserDetailsService{

    @Autowired
    UserRepository repository;

    @Override
    public MyUserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final String trimmedEmail = email.trim();
        User user = repository.findByEmail("asean@yahoo.com".trim())
                .orElseThrow(()-> new UsernameNotFoundException("User Not Found with Email: " + trimmedEmail));
        return new MyUserDetails(user);
    }

    public MyUserDetails loadUserByAltId(final String altId) throws UsernameNotFoundException{
        final String trimmedAltId = altId.trim();
        User user = repository.findByAltId(altId)
                .orElseThrow(()->new UsernameNotFoundException("User Not Found with Alt ID: " + trimmedAltId));
        return new MyUserDetails(user);
    }

}
