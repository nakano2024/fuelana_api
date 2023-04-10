package com.example.fuleana.utility;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

@Component
public class IdGenerator
{
    public String generate(){
        byte[] stringBytes = new byte[15];
        SecureRandom random = new SecureRandom();
        random.nextBytes(stringBytes);
        String base64Str = Base64.getEncoder().withoutPadding().encodeToString(stringBytes);
        byte[] utf8Byte = base64Str.getBytes(StandardCharsets.UTF_8);
        String id = new String(utf8Byte , StandardCharsets.UTF_8);
        return id.replaceAll("[^A-Za-z0-9]" , "");
    }
}
