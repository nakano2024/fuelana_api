package com.example.fuleana.repository;

import com.example.fuleana.entity.Car;
import com.example.fuleana.entity.Purpose;
import com.example.fuleana.entity.TripRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TripRecordRepository extends JpaRepository<TripRecord , Long> {

    @Query("select t from TripRecord t where t.car = :car AND t.purpose = :purpose AND YEAR(t.createdAt) = :year")
    List<TripRecord> findByCarAndPurposeAndYear(@Param("car") Car car,@Param("purpose") Purpose purpose
            , @Param("year") Integer year);

}
