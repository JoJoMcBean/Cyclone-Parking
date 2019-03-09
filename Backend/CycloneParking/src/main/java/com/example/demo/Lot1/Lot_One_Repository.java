package com.example.demo.Lot1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Lot_One_Repository extends JpaRepository<Lot_One, Integer>{

    @Query(value = "SELECT spotnum FROM Lot_One WHERE isfilled = true")
    List<Integer> getFilled();

    @Query(value = "SELECT spotnum FROM Lot_One WHERE isfilled = false")
    List<Integer> getEmpty();
}
