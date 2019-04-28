package com.example.demo.Messages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface MessagesRepository extends JpaRepository<Messages, Integer> {

    @Query(value = "INSERT INTO messages (username, message, timestamp) VALUES(?1, ?2, ?3)", nativeQuery = true)
    @Modifying
    @Transactional
    void addMessage(String username, String message, Long timeStampMillis);
}
