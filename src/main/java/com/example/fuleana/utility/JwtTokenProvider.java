package com.example.fuleana.utility;


import com.example.fuleana.entity.MyUserDetails;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.security.Key;
import java.security.KeyPair;
import java.util.Date;
import java.util.stream.Collectors;

public class JwtTokenProvider {

    private Key secretKey;
    private long duraionInMilliseconds;

    public JwtTokenProvider(String secretKey, long durationInMilliSeconds){
        this.duraionInMilliseconds = durationInMilliSeconds;
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String createToken(@NotNull Authentication auth){

        @NotNull MyUserDetails userDetails = auth.getPrincipal() instanceof UserDetails?
                (MyUserDetails)auth.getPrincipal() : null;

        Date now = new Date();
        Date expiration = new Date(now.getTime() + duraionInMilliseconds);

        return Jwts.builder()
                .setSubject(userDetails.getAltId())
                .claim("authorities", auth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(secretKey , SignatureAlgorithm.HS256)
                .compact();

    }

    public String getSubjectByToken(String token) throws JwtException {
        String subject = "";
        subject = Jwts.parserBuilder().setSigningKey(secretKey)
                .build().parseClaimsJws(token).getBody().getSubject();
        return subject;
    }

}
