package com.example.fuleana.entity;

import com.example.fuleana.entity.pk.CarAuthPk;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "car_auth")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarAuth {

    @EmbeddedId
    private CarAuthPk carAuthId;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("carId")
    private Car car;

    @Column(name = "is_write")
    private boolean isWrite;

    @Column(name = "is_delete")
    private boolean isDelete;

}
