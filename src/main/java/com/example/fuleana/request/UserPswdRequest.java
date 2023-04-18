package com.example.fuleana.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserPswdRequest {

    @NotEmpty
    private String password;

}
