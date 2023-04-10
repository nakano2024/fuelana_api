package com.example.fuleana.entity.pk;

import com.example.fuleana.entity.Car;
import com.example.fuleana.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
@AllArgsConstructor
public class CarAuthPk {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "car_id")
    private Long carId;

}
