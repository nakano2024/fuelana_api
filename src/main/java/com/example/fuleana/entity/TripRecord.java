package com.example.fuleana.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "trip_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TripRecord {

    @Column(name = "trip_record_id")
    private Long tripRecordId;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "price")
    private Float price;

    @Column(name = "fuel_liter")
    private Float fuelLiter;

    @Column(name = "mileage")
    private Float mileage;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

}
