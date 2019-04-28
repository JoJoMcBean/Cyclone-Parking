package com.example.demo.CurrentlyParked;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CurrentlyParkedRepository extends JpaRepository<CurrentlyParked, Spot> {

    @Query(value = "SELECT spotnum FROM currently_parked WHERE lotid = ?1", nativeQuery = true)
    List<Integer> getFilledSpots(String lot);


    @Query(value = "DELETE  FROM currently_parked WHERE username = ?1", nativeQuery = true)
    @Modifying
    @Transactional
    void leaveSpot(String username);

    @Query(value = "SELECT default_users.license FROM default_users INNER JOIN user_login ON user_login.username = default_users.username WHERE user_login.token = ?1", nativeQuery = true)
    String getLicenseWithToken(String token);


    @Query(value = "INSERT INTO currently_parked VALUES(?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    @Modifying
    @Transactional
    void addParkedSpot(String username, String lot, Integer spot, String license, Long time);

    @Query(value = "SELECT * FROM currently_parked WHERE license = ?1", nativeQuery = true)
    String selectLicense(String license);

    @Query(value = "SELECT username from user_login WHERE token = ?1", nativeQuery = true)
    String getUsernameFromToken(String token);

    @Query(value = "SELECT COUNT(*) FROM parking_history WHERE username = ?1", nativeQuery = true)
    Integer getEntriesPerUser(String username);

    @Query(value = "DELETE FROM parking_history WHERE timestart IN (SELECT MIN(timestart) FROM (SELECT * FROM parking_history) as x WHERE username=?1)", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteOldest(String username);

    @Query(value = "INSERT INTO parking_history VALUES(?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    @Modifying
    @Transactional
    void insertEntry(String username, String lot, Integer spotnum, Long timestart, Long timeend, Double paid);


    @Query(value = "SELECT * FROM currently_parked WHERE username = ?1", nativeQuery = true)
    CurrentlyParked selectEntry(String username);

    @Query(value = "SELECT license FROM currently_parked WHERE username = ?1", nativeQuery = true)
    String getLicenseFromUsername(String username);
}
