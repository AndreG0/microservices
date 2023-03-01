package com.scooter.service.respository;

import com.scooter.service.entity.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScooterRepository extends JpaRepository<Scooter,Integer> {

    List<Scooter> findByUserId(int userId);
}
