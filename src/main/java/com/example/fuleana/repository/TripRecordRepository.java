package com.example.fuleana.repository;

import com.example.fuleana.entity.Car;
import com.example.fuleana.entity.TripRecord;
import com.example.fuleana.entity.TripRecordTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;


public interface TripRecordRepository extends JpaRepository<TripRecord , Long> {

    @Query("select t from TripRecord t " +
            "where t.car = :car " +
            "and YEAR(t.createdAt) = :year " +
            "and MONTH(t.createdAt) = :month")
    List<TripRecord> findByCarAndYearAndMonth(@Param("car") Car car, @Param("year") Integer year,
                                              @Param("month") Integer month );


    @Query("select new com.example.fuleana.entity.TripRecordTotal(tr.car, COALESCE(SUM(tr.totalYen), 0.0), COALESCE(SUM(tr.totalLiter), 0.0), COALESCE(SUM(tr.totalKilometers), 0.0) ) from TripRecord tr " +
            "where tr.car = :car " +
            "and YEAR(tr.createdAt) = :year " +
            "and MONTH(tr.createdAt) = :month")
    Optional<TripRecordTotal> getTripRecordTotalByCarAndYearAndMonth(@Param("car") Car car, @Param("year") int year,
                                                                    @Param("month") int month);

}
