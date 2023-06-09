package com.example.fuleana.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "fuel_price")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FuelPrice {

    @Column(name = "fuel_price_id")
    private Long fuelPriceId;

    @ManyToOne
    @JoinColumn(name = "fuel_type_id")
    private FuelType fuelType;

    @Column(name = "price_per_liter")
    private Float pricePerLiter;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

}
