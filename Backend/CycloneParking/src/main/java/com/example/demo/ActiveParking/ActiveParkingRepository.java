package com.example.demo.ActiveParking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ActiveParkingRepository extends JpaRepository<ActiveParking, String> {



    @Query(value = "SELECT * FROM active_parking", nativeQuery = true)
    List<ActiveParking> getAll();



    @Query(value = "SELECT * FROM active_parking WHERE username = ?1", nativeQuery = true)
    ActiveParking findByUsername(String username);





}