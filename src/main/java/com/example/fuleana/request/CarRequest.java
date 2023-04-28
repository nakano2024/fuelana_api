package com.example.fuleana.request;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CarRequest {

    @NotEmpty
    String discription;

    @NotEmpty
    @Pattern(regexp = "REGULAR|HIGH_OCTANE|DIESEL")
    String fuel_type_name;

    @NotNull
    @Min(0)
    float kilometers_per_liter;

}
