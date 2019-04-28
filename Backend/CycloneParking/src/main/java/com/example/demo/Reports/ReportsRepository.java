package com.example.demo.Reports;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ReportsRepository extends JpaRepository<Reports, Integer> {

    @Query(value = "Select * from reports sort ORDER by filetime DESC", nativeQuery = true)
    List<Reports> getReportsByDate();

    @Query(value = "Insert into reports(filedby, lot, spot, description, filetime) VALUES(?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    @Modifying
    @Transactional
    void fileReport(String filedby, String lot, Integer spot, String description, Long filetime);
}
