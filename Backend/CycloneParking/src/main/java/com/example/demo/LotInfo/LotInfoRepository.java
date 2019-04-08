package com.example.demo.LotInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotInfoRepository extends JpaRepository<LotInfo, String> {

    @Query(value = "SELECT * FROM lot_info WHERE lotid = ?1", nativeQuery = true)
    LotInfo getLotInfo(String lot);
}
