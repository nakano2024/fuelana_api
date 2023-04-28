package com.example.fuleana.repository;

import com.example.fuleana.entity.Car;
import com.example.fuleana.entity.Purpose;
import com.example.fuleana.entity.TripRecord;
import com.example.fuleana.entity.TripRecordsTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;


public interface TripRecordRepository extends JpaRepository<TripRecord , Long> {

    Optional<TripRecord> findByAltId(String altId);

    @Query("select t from TripRecord t " +
            "where t.car = :car " +
            "and t.purpose.name = :purpose_name " +
            "and YEAR(t.createdAt) = :year " +
            "and MONTH(t.createdAt) = :month")
    List<TripRecord> findByCarAndPurposeAndYearAndMonth(@Param("car") Car car,@Param("purpose_name") String purposeName , @Param("year") int year,
                                              @Param("month") int month );


    @Query("select new com.example.fuleana.entity.TripRecordsTotal(tr.car, tr.purpose, COALESCE(SUM(tr.totalYen), 0.0), COALESCE(SUM(tr.totalLiter), 0.0), COALESCE(SUM(tr.totalKilometers), 0.0) ) from TripRecord tr " +
            "where tr.car = :car " +
            "and tr.purpose.name = :purpose_name " +
            "and YEAR(tr.createdAt) = :year " +
            "and MONTH(tr.createdAt) = :month ")
    Optional<TripRecordsTotal> getTripRecordsTotalByCarAndPurposeAndYearAndMonth(@Param("car") Car car, @Param("purpose_name") String purposeName, @Param("year") int year,
                                                                                @Param("month") int month);

    @Query("select new com.example.fuleana.entity.TripRecordsTotal( tr.car, tr.purpose, COALESCE(SUM(tr.totalYen), 0.0), COALESCE(SUM(tr.totalLiter), 0.0), COALESCE(SUM(tr.totalKilometers), 0.0) ) from TripRecord tr " +
            "where tr.car = :car " +
            "and tr.purpose.name = :purpose_name " +
            "and YEAR(tr.createdAt) = :year ")
    Optional<TripRecordsTotal> getTripRecordsTotalByCarAndPurposeAndYear(@Param("car") Car car, @Param("purpose_name") String purposeName, @Param("year") int year);

}
