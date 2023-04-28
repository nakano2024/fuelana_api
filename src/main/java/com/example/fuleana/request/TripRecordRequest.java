package com.example.fuleana.request;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class TripRecordRequest {

    @NotEmpty
    @Pattern(regexp = "BUSINESS|PRIVATE")
    private String purpose_name;

    @NotNull
    @Min(0)
    private float total_kilometers;

}
