package com.example.demo.user_login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserLoginRepository extends JpaRepository<UserLogin, String> {



    @Query(value = "SELECT username FROM UserLogin", nativeQuery = true)
    List<String> getUsernames();



    @Query(value = "SELECT * FROM user_login WHERE username = ?1", nativeQuery = true)
    UserLogin getUser(String username);

    @Query(value = "UPDATE user_login SET token = ?1 WHERE username = ?2", nativeQuery = true)
    void addToken(String token, String username);



}
