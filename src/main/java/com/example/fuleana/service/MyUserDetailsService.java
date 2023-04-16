package com.example.fuleana.service;

import com.example.fuleana.entity.MyUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MyUserDetailsService extends UserDetailsService {


    MyUserDetails loadUserByAltId(final String altId);

}
