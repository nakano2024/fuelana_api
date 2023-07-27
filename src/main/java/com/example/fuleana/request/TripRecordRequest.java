package com.example.fuleana.request;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class TripRecordRequest {
    @NotEmpty
    @Pattern(regexp = "BUSINESS|PRIVATE")
    private String purposeName;

    @NotNull
    @Min(0)
    private float totalKilometers;
}
