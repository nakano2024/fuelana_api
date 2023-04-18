package com.example.fuleana.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Table(name = "car")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private long carId;

    @Column(name = "alt_id")
    private String altId;

    @Column(name = "discription")
    private String discription;

    @ManyToOne
    @JoinColumn(name = "fuel_type_id")
    private FuelType fuelType;

    @Column(name = "kilometers_per_liter")
    private float kilometersPerLiter;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

}
