package com.example.demo.user_login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserLoginRepository extends JpaRepository<UserLogin, String> {



    @Query(value = "SELECT username FROM UserLogin", nativeQuery = true)
    List<String> getUsernames();



    @Query(value = "SELECT * FROM user_login WHERE username = ?1", nativeQuery = true)
    UserLogin getUser(String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_login SET user_login.token = ?1 WHERE user_login.username = ?2", nativeQuery = true)
    void addToken(String token, String username);



}
