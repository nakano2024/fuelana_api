package com.example.fuleana.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticatedUserResponse {

    String name;
    String email;
    String role_name;
    boolean is_blocked;

}
