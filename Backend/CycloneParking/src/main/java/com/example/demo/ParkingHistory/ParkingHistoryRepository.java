package com.example.demo.ParkingHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ParkingHistoryRepository  extends JpaRepository<ParkingHistory, String> {

    @Query(value = "SELECT COUNT(*) FROM parking_history WHERE license = ?1", nativeQuery = true)
    Integer getEntriesPerUser(String license);

    @Query(value = "DELETE FROM parking_history WHERE timestart = (SELECT MIN(timestart)) && username = ?1", nativeQuery = true)
    void deleteOldest(String username);

    @Query(value = "INSERT INTO parking_history VALUES(?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    @Modifying
    @Transactional
    void insertEntry(String username, String lot, Long timestart, Long timeend, Double paid);


}
