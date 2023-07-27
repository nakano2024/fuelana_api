package com.example.fuleana.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticatedUserResponse {

    String userId;
    String name;
    String email;
    String roleName;
    boolean blocked;

}
