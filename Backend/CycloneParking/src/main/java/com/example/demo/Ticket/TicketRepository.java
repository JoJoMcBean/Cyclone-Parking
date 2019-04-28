package com.example.demo.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,LicenseDate> {

    @Query(value = "DELETE from ticket WHERE date = ?1 AND license = ?2", nativeQuery = true)
    @Modifying
    @Transactional
    void removeEntry(long date, String license);

    @Query(value = "SELECT * from ticket WHERE license = ?1 ORDER BY paid ASC, date DESC", nativeQuery = true)
    List<Ticket> searchByLicense(String license);

    @Query(value = "Select * from ticket sort ORDER by date DESC", nativeQuery = true)
    List<Ticket> getAll();

}
