package com.example.fuleana.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticatedUserResponse {

    String user_id;
    String name;
    String email;
    String role_name;
    boolean blocked;

}
