package com.example.fuleana.repository;

import com.example.fuleana.entity.Car;
import com.example.fuleana.entity.CarAuth;
import com.example.fuleana.entity.User;
import com.example.fuleana.entity.pk.CarAuthPk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarAuthRepository extends JpaRepository<CarAuth , CarAuthPk> {

    List<CarAuth> findByUser(User user);
    List<CarAuth> findByCar(Car car);

}
