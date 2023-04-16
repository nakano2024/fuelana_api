package com.example.fuleana.filter;

import com.example.fuleana.entity.MyUserDetails;
import com.example.fuleana.service.MyUserDetailsService;
import com.example.fuleana.utility.JwtTokenProvider;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthorizationFilter extends OncePerRequestFilter {

    JwtTokenProvider jwtTokenProvider;

    MyUserDetailsService userDetailsService;

    public JwtAuthorizationFilter(JwtTokenProvider jwtTokenProvider, MyUserDetailsService userDetailsService){
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String token = getTokenFromRequest(request);
            //System.out.println("token : " + token);
            if(StringUtils.hasText(token) ){
                //このメソッドの中でバリデーションが行われる
                final String userAltId = jwtTokenProvider.getSubjectByToken(token);
                MyUserDetails userDetails = userDetailsService.loadUserByAltId(userAltId);
                //System.out.println(userAltId);
                //System.out.println(userDetails.getUsername());
                Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null ,userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        catch (JwtException e){
            System.out.println(e);
        }
        filterChain.doFilter(request, response);

    }

    String getTokenFromRequest(HttpServletRequest req){
        final String bearerToken = req.getHeader("Authorization");
        //System.out.println(bearerToken);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return "";
    }

}
