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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_record_id")
    private long tripRecordId;

    @Column(name = "alt_id")
    private String altId;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "purpose_id")
    private Purpose purpose;

    @Column(name = "total_yen")
    private float totalYen;

    @Column(name = "total_liter")
    private float totalLiter;

    @Column(name = "total_kilometers")
    private float totalKilometers;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

}
