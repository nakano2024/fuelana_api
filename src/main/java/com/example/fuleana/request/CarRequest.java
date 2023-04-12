package com.example.fuleana.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CarRequest {

    @NotEmpty
    String discription;

    @NotEmpty
    String fuleTypeName;

    @NotEmpty
    Float kilometersPerLiter;

}
