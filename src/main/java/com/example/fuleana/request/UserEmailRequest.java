package com.example.fuleana.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserEmailRequest {

    @NotEmpty
    private String email;

}
